package com.example.mohinhmvvm.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TaiKhoanViewModel : ViewModel() {
    private val repository = TaiKhoanRepository()

    private val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser> get() = _user

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _user.value = repository.login(email, password)
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _user.value = repository.register(email, password)
        }
    }

    fun logout() {
        repository.logout()
        _user.value = null
    }
}
