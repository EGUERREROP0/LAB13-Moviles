package com.guerrero.erminio.pokeapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(
    private val sharedPreferencesRepository: SharedPreferencesRepository) :
    ViewModel() {

    val registrationSuccess = MutableLiveData<Boolean>()
    val registrationError = MutableLiveData<String>()

    fun registerUser(email: String, password: String, confirmPassword: String) {

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            registrationError.value = "Por favor, rellena todos los campos."
        } else if (!isValidEmail(email)) {
            registrationError.value = "Por favor, introduce un correo electrónico válido."
        } else if (password != confirmPassword) {
            registrationError.value = "Las contraseñas no coinciden."
        } else {
            sharedPreferencesRepository.saveUserEmail(email)
            sharedPreferencesRepository.saveUserPassword(password)
            registrationSuccess.value = true
        }
    }

    //validar Email
    private fun isValidEmail(target: CharSequence): Boolean {
        return !target.isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}