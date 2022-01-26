package com.emstell.clinix.network

import com.emstell.clinix.network.models.clinic.ClinicBody
import com.emstell.clinix.network.models.clinic.ClinicInfo
import com.emstell.clinix.network.models.clinic.ClinicResponse
import com.emstell.clinix.network.models.home.HomeModelResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("get_homepage")
    suspend fun callHomeData(@QueryMap homeQueryMap:Map<String, String>): Response<HomeModelResponse>

    @POST("get_clinic_detail")
    suspend fun callClinicData(@Body clinicBody: ClinicBody):Response<ClinicResponse>
}