package com.bams.apiskyscanner.models

data class OutboundLeg(
    val CarrierIds: List<Int>,
    val OriginId: Int,
    val DestinationId: Int,
    val DepartureDate: String
)