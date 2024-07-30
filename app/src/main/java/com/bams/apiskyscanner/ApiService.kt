package com.bams.apiskyscanner

import com.bams.apiskyscanner.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SkyScannerApi {
    @Headers("x-rapidapi-key: c56160301emsh243ce3701c9273cp152586jsnd065d0105553") // Ganti YOUR_API_KEY dengan kunci API Anda
    @GET("apiendpoint_7bbaa9db-a135-41d7-b5eb-d49036533066")
    fun getFlightDetails(@Query("param1") param1: String): Call<ApiResponse>
}
