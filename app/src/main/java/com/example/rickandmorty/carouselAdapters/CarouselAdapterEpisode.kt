package com.example.rickandmorty.carouselAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.EpisodeDetailActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.retrofitMihaiModels.MihaiCharacterModelEpisodes

class CarouselAdapterEpisode(context: Context ):
    RecyclerView.Adapter<CarouselAdapterEpisode.ViewHolder>() {


    private val mLayoutInflater : LayoutInflater = LayoutInflater.from(context)
    var mData: List<MihaiCharacterModelEpisodes> = listOf()
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
        holder.onBind( mData[position])
        holder.mImageEpisodes.setOnClickListener(View.OnClickListener {
            val intent = Intent( mLayoutInflater.context, EpisodeDetailActivity::class.java )
            intent.putExtra("episode_id",mData[position].id)
            mLayoutInflater.context.startActivity(intent)
        })
    }


    class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView)  {

        val mImageEpisodes = itemView.findViewById<TextView>(R.id.id_charDet_layout_image)

        fun onBind(mihaiCharacterModelDetailed: MihaiCharacterModelEpisodes) {
            mImageEpisodes.text = mihaiCharacterModelDetailed.episodesNumber
        }


    }

}