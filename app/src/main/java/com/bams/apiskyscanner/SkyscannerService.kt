package com.bams.apiskyscanner

import com.bams.apiskyscanner.models.FlightResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyscannerService {
    @GET("flights/live/search")
    fun searchFlights(
        @Query("market") market: String,
        @Query("currency") currency: String,
        @Query("locale") locale: String,
        @Query("originPlace") originPlace: String,
        @Query("destinationPlace") destinationPlace: String,
        @Query("outboundDate") outboundDate: String,
        @Query("adults") adults: Int,
        @Query("apiKey") apiKey: String
    ): Call<FlightResponse>
}
