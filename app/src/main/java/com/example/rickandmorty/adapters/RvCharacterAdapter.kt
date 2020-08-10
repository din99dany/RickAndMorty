package com.example.rickandmorty.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.CharacterDetailActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.retrofitCharacter.CharacterModel
import com.squareup.picasso.Picasso

class RvCharacterAdapter(context:Context, data:List<CharacterModel>): RecyclerView.Adapter<RvCharacterAdapter.ViewHolder>() {

    private val mLayoutInflater : LayoutInflater = LayoutInflater.from(context)
    var mData= data
        set( toChange ) {
            field = toChange
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = mLayoutInflater.inflate(
            R.layout.recycle_view_character_layout,
            parent,
            false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = mData.get(position)

        holder.bind( character )

        holder.mCardView.setOnClickListener( View.OnClickListener {
            val intent = Intent(mLayoutInflater.context, CharacterDetailActivity::class.java)
            intent.putExtra("char_id",character.id)
            mLayoutInflater.context.startActivity(intent)
        })

    }


    class ViewHolder( itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        private val mNameTextView: TextView = itemView.findViewById(R.id.id_character_rv_name) as TextView
        val mThumbnailView : ImageView = itemView.findViewById(R.id.id_character_rv_thumbnail) as ImageView
        val mAlive : ImageView = itemView.findViewById(R.id.imageView4) as ImageView
        val mGender : ImageView = itemView.findViewById(R.id.imageView5) as ImageView
        val mCardView : CardView = itemView.findViewById(R.id.id_character_rv_card) as CardView

        fun bind( character: CharacterModel )
        {
            mNameTextView.text = character.name
            when ( character.status )
            {
                "Alive" -> mAlive.setImageResource(R.drawable.ic_alive)
                "Dead" -> mAlive.setImageResource( R.drawable.ic_deadicon )
            }

            when ( character.gender )
            {
                "Male" -> mGender.setImageResource(R.drawable.male_icon)
                "Female" -> mGender.setImageResource( R.drawable.female_icon )
            }

            Picasso.get().
            load(character.thumbnail).
            into(mThumbnailView)
        }

    }

}