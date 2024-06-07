package com.guerrero.erminio.pokeapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class RegisterViewModelFactory(private val sharedPreferencesRepository: SharedPreferencesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(sharedPreferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}