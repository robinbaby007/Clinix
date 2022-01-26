package com.emstell.clinix.network.models.home

data class HomeModelResponse(
    val clinic_count: Int,
    val clinics: List<Clinic>,
    val doctor_count: Int,
    val doctors: List<Doctor>,
    val status: String
)