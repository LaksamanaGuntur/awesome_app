package com.example.awesomeapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.ui.activity.home.adapter.ItemHomeAdapter

class BindingUtils {

    companion object {
        @JvmStatic
        @BindingAdapter("adapter")
        fun addItemHomeAdapter(recyclerView: RecyclerView, items: List<Photo>?) {
            val adapterItem: ItemHomeAdapter? = recyclerView.adapter as? ItemHomeAdapter
            if (adapterItem != null) {
                adapterItem.clearItems()
                items?.let { adapterItem.addItems(it) }
            }
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, url: String?) {
            val context = imageView.context
            Glide.with(context)
                .load(url)
                .into(imageView)
        }
    }
}