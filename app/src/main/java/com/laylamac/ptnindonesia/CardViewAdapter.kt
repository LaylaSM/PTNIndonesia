package com.laylamac.ptnindonesia

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewAdapter(private val listCampus: ArrayList<Campus>) : RecyclerView.Adapter<CardViewAdapter.CardviewViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class CardviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvCity: TextView = itemView.findViewById(R.id.tv_item_city)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_set_favorite)
        var btnShare: Button = itemView.findViewById(R.id.btn_set_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CardviewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_campus, parent, false)
        return CardviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCampus.size
    }

    override fun onBindViewHolder(holder: CardviewViewHolder, position: Int) {
        val (name, city, photo) = listCampus[position]
        Glide.with(holder.itemView.context)
                .load(photo)
                .apply(RequestOptions())
                .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvCity.text = city

        holder.btnFavorite.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Favorite " + listCampus[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
        }

        holder.btnShare.setOnClickListener {
            Toast.makeText(holder.btnShare.context, "Share " + listCampus[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Kamu memilih " + listCampus[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
            onItemClickCallback.onItemClikced(listCampus[holder.adapterPosition])
        }

    }

    interface OnItemClickCallback {
        fun onItemClikced(data: Campus)
    }
}