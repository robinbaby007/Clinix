package com.emstell.clinix.network.models.home

import com.google.gson.annotations.SerializedName

data class Clinic(
    @SerializedName("24hour")
    val hour: String,
    val clinic_id: String,
    val favourite: String,
    val image: String,
    val location: String,
    val name: String,
    val rating: String,
    val service_info: List<ServiceInfo>
)