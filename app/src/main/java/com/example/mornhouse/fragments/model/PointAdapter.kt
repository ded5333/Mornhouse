package com.example.mornhouse.fragments.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mornhouse.room.Point
import com.example.mornhouse.R

class PointAdapter(
    var pointsFacts: List<Point>,
    private var clickerAdapter: ClickerAdapter,
) :
    RecyclerView.Adapter<PointAdapter.PointViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_point, parent, false)
        return PointViewHolder(view)
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(pointsFacts[position], clickerAdapter)

    }

    override fun getItemCount(): Int {
        return pointsFacts.size
    }

    class PointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private var pointFacts: TextView


        fun bind(pointItems: Point, clickerAdapter: ClickerAdapter) {
            pointFacts.text = pointItems.pointFacts


            itemView.setOnClickListener {
                clickerAdapter.onclick(pointItems)
            }

        }

        init {
            pointFacts = itemView.findViewById(R.id.pointFacts)


        }


    }
}