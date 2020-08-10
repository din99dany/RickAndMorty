package com.example.rickandmorty.carouselAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R

class CarouselAdapter(context: Context, data:List<String>): RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {


    private val mLayoutInflater : LayoutInflater = LayoutInflater.from(context)
    var mData = data
        set( toChange ) {
            field = toChange
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = mLayoutInflater.inflate(
            R.layout.recycle_view_character_details_layout,
            parent,
            false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mImageEpisodes.text = position.toString()
    }


    class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val mImageEpisodes = itemView.findViewById<TextView>(R.id.id_charDet_layout_image)
    }

}