package com.example.mohinhmvvm.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class SachViewModel : ViewModel() {
    private val repository = SachRepository()

    private val _listSach = MutableLiveData<List<Sach>>()
    val listSach: LiveData<List<Sach>> get() = _listSach

    fun loadData() {
        viewModelScope.launch {
            _listSach.value = repository.getAll()
        }
    }

    fun addSach(sach: Sach) {
        viewModelScope.launch {
            repository.insert(sach)
            loadData()
        }
    }

    fun updateSach(sach: Sach) {
        viewModelScope.launch {
            repository.update(sach)
            loadData()
        }
    }

    fun deleteSach(id: String) {
        viewModelScope.launch {
            repository.delete(id)
            loadData()
        }
    }
}
