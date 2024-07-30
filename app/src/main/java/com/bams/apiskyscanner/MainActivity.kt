package com.bams.apiskyscanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bams.apiskyscanner.models.FlightResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TicketAdapter
    private lateinit var flightList: List<Flight>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        flightList = listOf()
        adapter = TicketAdapter(flightList)
        recyclerView.adapter = adapter

        // Fetch flight data from API and update adapter
        fetchFlightData()
    }

    private fun fetchFlightData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://partners.api.skyscanner.net/apiservices/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SkyscannerService::class.java)
        val call = service.searchFlights(
            market = "US",
            currency = "USD",
            locale = "en-US",
            originPlace = "SFO-sky",
            destinationPlace = "LAX-sky",
            outboundDate = "2024-08-01",
            adults = 1,
            apiKey = "f8c99b12bemsh5cbe9515d2b3780p129efdjsn77806d3036b6" // Ganti dengan API key Anda
        )

        call.enqueue(object : Callback<FlightResponse> {
            override fun onResponse(call: Call<FlightResponse>, response: Response<FlightResponse>) {
                if (response.isSuccessful) {
                    val flightResponse = response.body()
                    if (flightResponse != null) {
                        // Handle the response, update flightList and adapter
                        flightList = convertToFlightList(flightResponse)
                        adapter.updateData(flightList)
                    }
                }
            }

            override fun onFailure(call: Call<FlightResponse>, t: Throwable) {
                // Handle the error
            }
        })
    }

    private fun convertToFlightList(flightResponse: FlightResponse): List<Flight> {
        val flights = mutableListOf<Flight>()
        for (quote in flightResponse.Quotes) {
            val carrierName = flightResponse.Carriers.find { it.CarrierId == quote.OutboundLeg.CarrierIds[0] }?.Name ?: "Unknown"
            val departureTime = quote.OutboundLeg.DepartureDate
            flights.add(Flight(carrierName, quote.MinPrice, departureTime))
        }
        return flights
    }
}

