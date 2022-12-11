package com.example.tp_kotlin_modesto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_kotlin_modesto.databinding.LayoutItemMainBinding
import com.example.tp_kotlin_modesto.response.QuoteResponse

class ImageListAdapter(
    val onClick: (item: QuoteResponse) -> Unit
): RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder>(){

    var data: List<QuoteResponse> = listOf()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_main, parent, false)
        return ImageListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, onClick)
    }
    override fun getItemCount(): Int = data.size

    class ImageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: LayoutItemMainBinding = LayoutItemMainBinding.bind(itemView)

        fun bind(item: QuoteResponse,onClick: (item:QuoteResponse)->Unit) {
            Glide
                .with(itemView.context)
                .load(item.urls.raw)
                .into(binding.imageView2)

            binding.imageView2.setOnClickListener{
                onClick(item)
            }
        }
    }
}