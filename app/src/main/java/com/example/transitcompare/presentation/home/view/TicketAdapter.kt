package com.example.transitcompare.presentation.home.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.transitcompare.R
import com.example.transitcompare.data.datasource.remote.model.TicketViewData

class TicketAdapter(
    private val itemClickListener: ItemClickListener,
) :
    RecyclerView.Adapter<TicketAdapter.ViewHolder>() {
    private var tickets: ArrayList<TicketViewData> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(tickets: List<TicketViewData>, clearAndAdd: Boolean) {
        if (clearAndAdd) {
            this.tickets.clear()
        }
        this.tickets.addAll(tickets)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var detailTicket: ConstraintLayout? = null
        var icViewDetail: ImageView? = null
        var timeStart: TextView? = null
        var timeEnd: TextView? = null
        var totalTime: TextView? = null
        var detailButton: carbon.widget.LinearLayout? = null

        init {
            detailTicket = itemView.findViewById(R.id.view_detail_ticket)
            icViewDetail = itemView.findViewById(R.id.ic_down_arrow)
            detailButton = itemView.findViewById(R.id.button_details)
            timeStart = itemView.findViewById(R.id.time_start)
            timeEnd = itemView.findViewById(R.id.time_end)
            totalTime = itemView.findViewById(R.id.total_time)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_ticket_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (tickets[position].viewDetail) {
            holder.detailTicket?.visibility = View.VISIBLE
            holder.icViewDetail?.rotation = 0f
        } else {
            holder.detailTicket?.visibility = View.GONE
            holder.icViewDetail?.rotation = 180f
        }

        holder.timeStart?.text = tickets[position].arrivalTime
        holder.timeEnd?.text = tickets[position].departureTime
        holder.totalTime?.text = tickets[position].totalTime

        holder.detailButton?.setOnClickListener {
            itemClickListener.viewDetail(position)
        }
        if (position >= itemCount - 1) {
            itemClickListener.loadMoreTickets()
        }
    }

    interface ItemClickListener {
        fun viewDetail(position: Int)

        fun loadMoreTickets()
    }
}