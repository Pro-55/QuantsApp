package com.example.quantsapp.data.repository.contract

import com.example.quantsapp.models.Data
import com.example.quantsapp.models.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun fetchFeed(): Flow<Resource<Data>>

}