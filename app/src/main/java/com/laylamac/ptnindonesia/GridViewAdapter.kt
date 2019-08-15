package com.laylamac.ptnindonesia

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridViewAdapter (private val listCampus : ArrayList<Campus>): RecyclerView.Adapter<GridViewAdapter.GridviewViewHolder>() {

    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback (onItemClickCallback : OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class GridviewViewHolder (itemView :View): RecyclerView.ViewHolder(itemView){
        var imgPhoto : ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int):GridviewViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_campus, parent, false)
        return GridviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCampus.size
    }

    override fun onBindViewHolder(holder: GridviewViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(listCampus[position].photo)
                .apply(RequestOptions())
                .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listCampus[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked (data : Campus)
    }
}