package com.bams.apiskyscanner.models

data class Quote(
    val MinPrice: Float,
    val Direct: Boolean,
    val OutboundLeg: OutboundLeg
)