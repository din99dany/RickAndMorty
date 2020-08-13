package com.example.rickandmorty.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.EpisodeDetailActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.retrofitMihaiModels.MihaiEpisodeModel

class RvEpisodeAdapter( context:Context ): RecyclerView.Adapter<RvEpisodeAdapter.ViewHolder>() {

    private var mInflater = LayoutInflater.from(context)
    var mData : List<MihaiEpisodeModel> = listOf()
        set( toChange ) {
            field = toChange
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(
            R.layout.recycle_view_episode_layout,
            parent,
            false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData.get(position))
        holder.onClick(mInflater.context)
    }




    class ViewHolder( itemView: View ) : RecyclerView.ViewHolder(itemView) {

        private val mName = itemView.findViewById<TextView>(R.id.id_episodes_rv_name)
        private val mDate = itemView.findViewById<TextView>(R.id.id_episodes_rv_date)
        private val mSeason = itemView.findViewById<TextView>(R.id.id_episodes_rv_season)
        private val mCard = itemView.findViewById<CardView>(R.id.id_episodes_rv_card)
        private var mId = -1

        fun bind( valoare:MihaiEpisodeModel ) {
            mName.text = valoare.name
            mDate.text = valoare.date
            mSeason.text = valoare.episode
            mId = valoare.id
        }

        fun onClick( context: Context ) {
            mCard.setOnClickListener( View.OnClickListener {
                val detailIntent = Intent( context, EpisodeDetailActivity::class.java)
                detailIntent.putExtra("episode_id",mId)
                context.startActivity(detailIntent)
            })
        }

    }

}