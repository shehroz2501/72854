package com.example.shahrozkhan_72854.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shahrozkhan_72854.R
import com.example.shahrozkhan_72854.activities.DetailActivity
import com.example.shahrozkhan_72854.models.ViewAllModel

class ViewAllAdapter(private val context: Context, private val list: List<ViewAllModel>) :
        RecyclerView.Adapter<ViewAllAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.view_img)
        val name: TextView = itemView.findViewById(R.id.view_name)
        val description: TextView = itemView.findViewById(R.id.view_desc)
        val price: TextView = itemView.findViewById(R.id.view_price)
        val rating: TextView = itemView.findViewById(R.id.view_rating)

        init {
        itemView.setOnClickListener {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("detail", list[position])
        context.startActivity(intent)
        }
        }
        }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_all_item, parent, false)
        return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        Glide.with(context).load(currentItem.image_url).into(holder.img)
        holder.name.text = currentItem.name
        holder.description.text = currentItem.description
        holder.price.text = currentItem.price
        holder.rating.text = currentItem.rating
        }

        override fun getItemCount(): Int {
        return list.size
        }
        }
