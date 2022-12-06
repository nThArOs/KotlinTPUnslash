package com.example.tp_kotlin_modesto

import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tp_kotlin_modesto.response.QuoteResponse
import kotlinx.coroutines.launch


class RetrofitViewModel (
    private val retrofitService: RetrofitService
) : ViewModel(){

    private val _data = MutableLiveData<QuoteResponse>()
    val data: LiveData<QuoteResponse> = _data

    init {
        fetchData()
    }

    fun refresh() {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _data.value = retrofitService.getData().body()
        }
    }
}

class RetrofitViewModelFactory(
    private val retrofitService: RetrofitService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)) {
            RetrofitViewModel(retrofitService) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
