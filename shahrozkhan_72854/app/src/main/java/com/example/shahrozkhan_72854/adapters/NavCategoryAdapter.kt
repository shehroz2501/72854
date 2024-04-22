package com.example.shahrozkhan_72854.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shahrozkhan_72854.R
import com.example.shahrozkhan_72854.models.NavCategoryModel

class NavCategoryAdapter(var context: Context, var list: List<NavCategoryModel>) :
    RecyclerView.Adapter<NavCategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.nav_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(list[position].img_url).into(holder.imageView)
        holder.name.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var name: TextView

        init {
            imageView = itemView.findViewById(R.id.cat_nav_img)
            name = itemView.findViewById(R.id.nav_cat_name)
        }
    }
}
