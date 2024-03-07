package com.example.akbarlaptops

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListLaptopAdapter(private val laptops: List<Laptop>) : RecyclerView.Adapter<ListLaptopAdapter.LaptopViewHolder>() {

    private var itemClickListener: ((Laptop) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaptopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_laptop, parent, false)
        return LaptopViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaptopViewHolder, position: Int) {
        val laptop = laptops[position]
        holder.bind(laptop)
    }

    override fun getItemCount(): Int = laptops.size

    fun setOnItemClickListener(listener: (Laptop) -> Unit) {
        itemClickListener = listener
    }

    inner class LaptopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        private val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

        fun bind(laptop: Laptop) {
            imgPhoto.setImageResource(laptop.photo)
            tvName.text = laptop.name
            tvDescription.text = laptop.description
            itemView.setOnClickListener { itemClickListener?.invoke(laptop) }
        }
    }
}
