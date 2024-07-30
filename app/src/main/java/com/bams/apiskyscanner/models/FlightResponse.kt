package com.bams.apiskyscanner.models

data class FlightResponse(
    val Quotes: List<Quote>,
    val Carriers: List<Carrier>,
    val Places: List<Place>
)
