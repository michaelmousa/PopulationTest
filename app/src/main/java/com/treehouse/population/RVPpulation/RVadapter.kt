package com.treehouse.population.RVPpulation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.treehouse.population.R
import kotlinx.android.synthetic.main.item_pass.view.*

class RVadapter : RecyclerView.Adapter<RVadapter.RVViewHolder>() {

    private val data = arrayListOf<ResultsData>()

    fun setData(items: List<ResultsData>) {
        this.data.clear()
        this.data.addAll(items)
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_pass, parent, false)
        return RVViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(viewHolder: RVViewHolder, position: Int) {
            viewHolder.bind(data[position])
        }

        class RVViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

            fun bind(data: ResultsData) {
                val tvCountry = rootView.findViewById <TextView> (R.id.tvCountry)
                tvCountry.text = data.country.toString()

                val tvAge = rootView.findViewById<TextView>(R.id.tvAge)
                tvAge.text = data.age.toString()


            }


        }
    }
