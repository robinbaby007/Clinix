package com.emstell.clinix.network.models.clinic

data class ClinicImage(
    val clinic_id: String,
    val clinic_image_id: String,
    val image: String,
    val sort_order: String
)