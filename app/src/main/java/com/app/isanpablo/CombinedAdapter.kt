package com.app.isanpablo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


class CombinedAdapter(private val items: List<Pair<Int, String>>, private val viewPager2: ViewPager2) : RecyclerView.Adapter<CombinedAdapter.CombinedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombinedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tourism_container, parent, false)
        return CombinedViewHolder(view)
    }

    override fun onBindViewHolder(holder: CombinedViewHolder, position: Int)     {
        val (imageResId, title) = items[position]
        holder.imageView.setImageResource(imageResId)
        holder.titleTextView.text = title
    }

    override fun getItemCount() = items.size

    class CombinedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.textView)
    }
}
