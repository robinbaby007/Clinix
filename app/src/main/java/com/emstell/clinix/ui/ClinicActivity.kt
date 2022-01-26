package com.emstell.clinix.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.emstell.clinix.R
import com.emstell.clinix.databinding.ActivityClinicBinding
import com.emstell.clinix.network.Utility
import com.emstell.clinix.network.models.clinic.ClinicBody
import com.emstell.clinix.network.models.clinic.ClinicResponse
import com.emstell.clinix.ui.Adapters.ClinicDetailedServiceAdapter
import com.emstell.clinix.ui.Adapters.ClinicWorkingHoursAdapter
import com.emstell.clinix.viewmodel.ClinicActivityViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClinicActivity : AppCompatActivity() {
    private lateinit var activityClinicBinding: ActivityClinicBinding
    private lateinit var clinicId:String
    private lateinit var clinicActivityViewModel: ClinicActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityClinicBinding=ActivityClinicBinding.inflate(layoutInflater)
        setContentView(activityClinicBinding.root)
        clinicId= intent.getStringExtra("clinicId")!!
        clinicActivityViewModel=ViewModelProvider(this)[ClinicActivityViewModel::class.java]
        lifecycleScope.launch(Dispatchers.IO) {
            val clinicBody=ClinicBody(clinicId,"124","2","1",Utility.API_KEY,"124")
            clinicActivityViewModel.callClinicDetails(clinicBody)
        }
        clinicActivityViewModel.getClinicData().observe(this,{
            setUIData(it)
        })

    }

    private fun setUIData(clinicResponse: ClinicResponse){
        Glide.with(this@ClinicActivity).apply {
            load(Utility.IMAGE_URL+clinicResponse.clinic_info.image).into(activityClinicBinding.idImageViewHospital)
            load(Utility.IMAGE_URL+"location.png").into(activityClinicBinding.idImageViewLocationIcon)
        }
        activityClinicBinding.apply {
            idTextViewClinicName.text=clinicResponse.clinic_info.name
            idTextViewLocationName.text=clinicResponse.clinic_info.location
            idTextViewDescriptionValue.text=clinicResponse.clinic_info.description
            idTextViewMobileNumber.text=clinicResponse.contact_details.mobile
            idTextViewEmail.text=clinicResponse.contact_details.email

            idRecyclerViewServices.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter= ClinicDetailedServiceAdapter(this@ClinicActivity,clinicResponse.clinic_services)
            }
            idRecyclerWorkingHours.apply {
                layoutManager = LinearLayoutManager(this@ClinicActivity)
                adapter= ClinicWorkingHoursAdapter(clinicResponse.workinghours)
            }


         }


    }
}