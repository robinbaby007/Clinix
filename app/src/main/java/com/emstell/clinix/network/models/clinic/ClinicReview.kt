package com.emstell.clinix.network.models.clinic

data class ClinicReview(
    val author: String,
    val author_image: String,
    val rating: String,
    val text: String
)