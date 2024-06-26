package com.example.transitcompare.presentation.home.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.transitcompare.R
import com.example.transitcompare.data.datasource.remote.model.FilterOptionModelViewData

enum class IconOption{
    Filters
}

class OptionFilterAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<OptionFilterAdapter.ViewHolder>() {
    private var options: ArrayList<FilterOptionModelViewData> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView? = null
        var title: TextView? = null
        var desc: TextView? = null

        init {
            icon = itemView.findViewById(R.id.icon)
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.description)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(options: List<FilterOptionModelViewData>) {
        this.options.clear()
        this.options.addAll(options)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_option_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = options[position].title
        holder.desc?.text = options[position].description
        holder.icon?.visibility = View.GONE

        val icon = options[position].icon
        if (!icon.isNullOrEmpty()) {
            when(icon){
                IconOption.Filters.name -> {
                    holder.icon?.visibility = View.VISIBLE
                    holder.desc?.visibility = View.GONE
                    holder.icon?.setColorFilter(R.drawable.ic_filter)
                }
            }
        }


        if(options[position].isSelected == true){
            holder.title?.setTextColor(Color.WHITE)
            holder.desc?.setTextColor(Color.WHITE)
            holder.icon?.setColorFilter(Color.WHITE)
            holder.itemView?.setBackgroundColor(Color.parseColor("#56BCD2"))
        }else{
            holder.title?.setTextColor(Color.BLACK)
            holder.desc?.setTextColor(Color.BLACK)
            holder.icon?.setColorFilter(Color.BLACK)
            holder.itemView?.setBackgroundColor(Color.WHITE)
        }

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(position)
        }
    }

    interface ItemClickListener {
        fun onClick(position: Int)
    }
}