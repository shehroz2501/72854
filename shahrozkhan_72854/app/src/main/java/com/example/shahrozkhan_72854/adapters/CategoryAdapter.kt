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
import com.example.shahrozkhan_72854.activities.ViewAllActivity
import com.example.shahrozkhan_72854.models.CategoryModel

class CategoryAdapter(var context: Context, var categoryList: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(categoryList[position].imageURL).into(holder.catImg)
        holder.name.text = categoryList[position].name
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ViewAllActivity::class.java)
            intent.putExtra("type", categoryList[position].type)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var catImg: ImageView

        init {
            catImg = itemView.findViewById(R.id.cat_img)
            name = itemView.findViewById(R.id.cat_name)
        }
    }
}
