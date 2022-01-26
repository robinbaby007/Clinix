package com.emstell.clinix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.emstell.clinix.network.models.clinic.ClinicBody
import com.emstell.clinix.network.models.clinic.ClinicResponse
import com.emstell.clinix.repository.ClinicActivityRepository

class ClinicActivityViewModel (application: Application):AndroidViewModel(application){

   private val clinicRepository=ClinicActivityRepository()
    private val clinicData:LiveData<ClinicResponse>
    init {
        clinicData=clinicRepository.clinicData
    }

    suspend fun callClinicDetails(clinicBody: ClinicBody){
        clinicRepository.callClinicDetails(clinicBody)
    }
    fun getClinicData():LiveData<ClinicResponse>{
        return clinicData
    }

}