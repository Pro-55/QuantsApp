package com.example.quantsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.quantsapp.data.repository.impl.MainRepositoryImpl
import com.example.quantsapp.models.Data
import com.example.quantsapp.models.Resource

class MainViewModel : ViewModel() {

    private val mainRepository by lazy { MainRepositoryImpl() }

    fun fetchFeed(): LiveData<Resource<Data>> = mainRepository.fetchFeed().asLiveData()

}