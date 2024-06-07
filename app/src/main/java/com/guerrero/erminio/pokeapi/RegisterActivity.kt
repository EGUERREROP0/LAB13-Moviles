package com.guerrero.erminio.pokeapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.guerrero.erminio.pokeapi.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferencesRepository = SharedPreferencesRepository()
        sharedPreferencesRepository.setSharedPreferences(this)
        viewModel = ViewModelProvider(this,
                             RegisterViewModelFactory(sharedPreferencesRepository))
                             .get(RegisterViewModel::class.java)

        setupRegisterButton()
        setupAlreadyAccountButton()
        observeRegistrationSuccess()
        observeRegistrationError()
    }

    private fun setupRegisterButton() {
        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtPassword2.text.toString()
            viewModel.registerUser(email, password, confirmPassword)
        }
    }

    private fun setupAlreadyAccountButton() {
        binding.btnAlreadyAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun observeRegistrationSuccess() {
        viewModel.registrationSuccess.observe(this, Observer { success ->
            if (success) {
                AlertDialog.Builder(this)
                    .setTitle("Registro exitoso")
                    .setMessage("Has sido registrado exitosamente.")
                    .setPositiveButton("OK") { _, _ ->
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .show()
            }
        })
    }

    private fun observeRegistrationError() {
        viewModel.registrationError.observe(this, Observer { error ->
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(error)
                .setPositiveButton("Aceptar", null)
                .show()
        })
    }
}