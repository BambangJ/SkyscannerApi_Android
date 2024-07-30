package com.bams.apiskyscanner.models

data class ApiResponse(
    val data: List<FlightDetail>
)

data class FlightDetail(
    val airline: String,
    val flightNumber: String,
    val departure: String,
    val arrival: String
)

