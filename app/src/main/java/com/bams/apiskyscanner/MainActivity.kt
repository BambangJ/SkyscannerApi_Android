package com.bams.apiskyscanner

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bams.apiskyscanner.models.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var tvResults: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResults = findViewById(R.id.tvResults)

        val api = RetrofitClient.instance.create(SkyScannerApi::class.java)
        api.getFlightDetails("value").enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val flightDetails = response.body()?.data
                    flightDetails?.forEach {
                        tvResults.append("Airline: ${it.airline}\nFlight Number: ${it.flightNumber}\nDeparture: ${it.departure}\nArrival: ${it.arrival}\n\n")
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                tvResults.text = "Error: ${t.message}"
            }
        })
    }
}