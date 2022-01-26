package com.emstell.clinix.network.models.home

data class Doctor(
    val clinic_id: String,
    val clinicname: String,
    val doctor_id: String,
    val favourite: String,
    val image: String,
    val location: String,
    val name: String,
    val rating: String,
    val service_info: List<ServiceInfoX>
)