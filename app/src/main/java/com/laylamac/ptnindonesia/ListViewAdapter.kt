package com.laylamac.ptnindonesia

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListViewAdapter(private val listCampus: ArrayList<Campus>) : RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvCity: TextView = itemView.findViewById(R.id.tv_item_city)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view :View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_campus, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCampus.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, city, photo, logo, web, telp, email) = listCampus[position]
        val mContext = holder.itemView.context
        Glide.with(holder.itemView.context)
                .load(photo)
                .apply(RequestOptions().override(50, 50))
                .into(holder.imgPhoto)


        holder.tvName.text = name
        holder.tvCity.text = city

                holder.itemView.setOnClickListener {
                    val detail = Intent(mContext, DetailActivity::class.java)
                    detail.putExtra(DetailActivity.EXTRA_NAME, name)
                    detail.putExtra(DetailActivity.EXTRA_CITY, city)
                    detail.putExtra(DetailActivity.EXTRA_IMAGE, photo)
                    detail.putExtra(DetailActivity.EXTRA_LOGO, logo)
                    detail.putExtra(DetailActivity.EXTRA_WEB, web)
                    detail.putExtra(DetailActivity.EXTRA_TELP, telp)
                    detail.putExtra(DetailActivity.EXTRA_EMAIL, email)
                    mContext.startActivity(detail)
                }.toString()

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Campus)
    }
}
