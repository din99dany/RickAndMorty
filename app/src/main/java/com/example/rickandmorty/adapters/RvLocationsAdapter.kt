package com.example.rickandmorty.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.LocationDetailActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.retrofitMihaiModels.MihaiLocationModel

class RvLocationsAdapter( context: Context ) : RecyclerView.Adapter<RvLocationsAdapter.ViewHolder>() {

    private val mInflater = LayoutInflater.from(context)
    var mData: List<MihaiLocationModel> = listOf()
        set( data ) {
            field = data
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(
            R.layout.recycle_view_locations_layout,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind( mData[position] )
        holder.onClick( mInflater.context )
    }

    class ViewHolder( itemView: View ) : RecyclerView.ViewHolder(itemView) {

        private val mName = itemView.findViewById<TextView>(R.id.id_locations_rv_name)
        private val mType = itemView.findViewById<TextView>(R.id.id_locations_rv_type)
        private val mDims = itemView.findViewById<TextView>(R.id.id_locations_rv_dimension)
        private val mCard = itemView.findViewById<CardView>(R.id.id_locations_rv_card)
        private var mId: Int = -1

        fun bind( loc:MihaiLocationModel ) {
            mName.text = loc.name
            mType.text = loc.type
            mDims.text = loc.dimension
            mId = loc.id
        }

        fun onClick( context: Context ) {
            mCard.setOnClickListener( View.OnClickListener {
                val detailIntet = Intent(context, LocationDetailActivity::class.java)
                detailIntet.putExtra("location_id", mId)
                context.startActivity(detailIntet)
            })
        }

    }

}