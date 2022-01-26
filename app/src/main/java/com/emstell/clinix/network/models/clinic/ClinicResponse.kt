package com.emstell.clinix.network.models.clinic

data class ClinicResponse(
    val Insurancestatus: String,
    val clinic_images: List<ClinicImage>,
    val clinic_info: ClinicInfo,
    val clinic_reviews: List<ClinicReview>,
    val clinic_services: List<ClinicService>,
    val contact_details: ContactDetails,
    val status: String,
    val workinghours: List<Workinghour>
)