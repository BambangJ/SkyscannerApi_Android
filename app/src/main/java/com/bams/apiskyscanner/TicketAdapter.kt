package com.bams.apiskyscanner
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketAdapter(private var flightList: List<Flight>) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    class TicketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCarrier: TextView = view.findViewById(R.id.tvCarrier)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvDeparture: TextView = view.findViewById(R.id.tvDeparture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val flight = flightList[position]
        holder.tvCarrier.text = flight.carrierName
        holder.tvPrice.text = "${flight.price} USD"
        holder.tvDeparture.text = flight.departureTime
    }

    override fun getItemCount(): Int {
        return flightList.size
    }

    fun updateData(newFlightList: List<Flight>) {
        flightList = newFlightList
        notifyDataSetChanged()
    }
}

