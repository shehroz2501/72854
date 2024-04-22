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
import com.example.shahrozkhan_72854.models.PopularModel

class PopularAdapter(
    private val context: Context,
    private val popularModelList: List<PopularModel>
) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.popular_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(popularModelList[position].imageURL).into(holder.popImage)
        holder.name.text = popularModelList[position].name
        holder.description.text = popularModelList[position].description
        holder.rating.text = popularModelList[position].rating
    }

    override fun getItemCount(): Int {
        return popularModelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var popImage: ImageView
        var name: TextView
        var description: TextView
        var rating: TextView

        init {
            popImage = itemView.findViewById(R.id.pop_img)
            name = itemView.findViewById(R.id.pop_name)
            description = itemView.findViewById(R.id.pop_des)
            rating = itemView.findViewById(R.id.pop_rating)
        }
    }
}
