package com.emstell.clinix.network.models.clinic

data class ClinicInfo(
    val `24hour`: String,
    val clinic_id: String,
    val description: String,
    val expiry: Int,
    val favourite: String,
    val image: String,
    val location: String,
    val name: String,
    val rating: String,
    val subscription_end: String
)