package com.emstell.clinix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.emstell.clinix.network.models.home.HomeModelResponse
import com.emstell.clinix.repository.HomeActivityRepository

class HomeActivityViewModel(application: Application): AndroidViewModel(application) {
    private val repository= HomeActivityRepository()
    private val homeResponse:LiveData<HomeModelResponse>
     init {
        homeResponse=repository.homeResponse
     }
    suspend fun callHomeData(){
        repository.callHomeData();
    }
    fun  getHomeResponse():LiveData<HomeModelResponse>{
        return homeResponse
    }
}