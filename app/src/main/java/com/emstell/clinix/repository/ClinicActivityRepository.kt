package com.emstell.clinix.repository

import androidx.lifecycle.MutableLiveData
import com.emstell.clinix.network.ApiClient
import com.emstell.clinix.network.models.clinic.ClinicBody
import com.emstell.clinix.network.models.clinic.ClinicResponse

class ClinicActivityRepository {
    val clinicData=MutableLiveData<ClinicResponse>()
    suspend fun callClinicDetails(clinicBody: ClinicBody){
       val response= ApiClient.getApiClient().callClinicData(clinicBody)
        if (response.isSuccessful)
            clinicData.postValue(response.body())

    }
}