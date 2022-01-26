package com.emstell.clinix.network.models.clinic

data class ClinicBody(
    val clinic_id: String,
    val customer_id: String,
    val device_type: String,
    val language_id: String,
    val token: String,
    val user_id: String
)