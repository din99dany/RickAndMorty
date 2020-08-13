package com.example.rickandmorty.carouselAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.CharacterDetailActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.retrofitMihaiModels.MihaiUltraLowCharacter
import com.squareup.picasso.Picasso

class CarouselLowCharacter(context: Context):
RecyclerView.Adapter<CarouselLowCharacter.ViewHolder>() {


    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)
    var mData: List<MihaiUltraLowCharacter> = listOf()
    set(toChange) {
        field = toChange
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mLayoutInflater.inflate(
            R.layout.recycle_view_episode_details_layout,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mData[position])
        holder.mImage.setOnClickListener(View.OnClickListener {
            val intent = Intent( mLayoutInflater.context, CharacterDetailActivity::class.java )
            intent.putExtra("char_id",mData[position].id)
            mLayoutInflater.context.startActivity(intent)
        })
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mImage = itemView.findViewById<ImageView>(R.id.id_episodeDet_layout_image)

        fun onBind(toLoad: MihaiUltraLowCharacter) {
            Picasso.get()
                .load(toLoad.image)
                .into(mImage)
        }


    }
}