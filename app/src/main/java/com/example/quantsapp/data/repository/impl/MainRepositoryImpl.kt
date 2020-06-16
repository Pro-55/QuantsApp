package com.example.quantsapp.data.repository.impl

import com.example.quantsapp.data.network.Retrofit
import com.example.quantsapp.data.repository.contract.MainRepository
import com.example.quantsapp.models.Data
import com.example.quantsapp.models.Resource
import com.example.quantsapp.util.extensions.resourceFlow
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl : MainRepository {

    companion object {
        private val TAG = MainRepositoryImpl::class.java.simpleName
    }

    override fun fetchFeed(): Flow<Resource<Data>> {
        return resourceFlow {
            val result = Retrofit.quantsApi.fetchFeed()

            if (result.isSuccessful) {
                val feed = result.body() ?: Data()
                emit(Resource.success(feed))
            } else {
                val msg = result.message()
                emit(Resource.error(msg))
            }
        }
    }

}