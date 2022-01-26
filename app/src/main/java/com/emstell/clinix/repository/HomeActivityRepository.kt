package com.emstell.clinix.repository

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emstell.clinix.R
import com.emstell.clinix.network.ApiClient
import com.emstell.clinix.network.ApiInterface
import com.emstell.clinix.network.models.home.HomeModelResponse

class HomeActivityRepository {

    val homeResponse=MutableLiveData<HomeModelResponse>()
    suspend fun callHomeData(){
        val homeQueryMap=HashMap<String,String>()
        homeQueryMap["language_id"] = "1"
        homeQueryMap["token"] ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMTI0IiwiaWF0IjoxNjM4NDM5NjE0fQ.7qm4dyI1G3kMf-VAwWrNS_F_D2LjZDX9Wew-HBYUjd4"
        homeQueryMap["user_id"] = "124"
        val response=ApiClient.getApiClient().callHomeData(homeQueryMap)
            if (response.isSuccessful)
                homeResponse.postValue(response.body())
     }

}