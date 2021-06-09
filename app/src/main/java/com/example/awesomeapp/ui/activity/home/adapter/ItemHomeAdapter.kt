package com.example.awesomeapp.ui.activity.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeapp.R
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.databinding.ItemEmptyBinding
import com.example.awesomeapp.databinding.ItemHomeBinding
import com.example.awesomeapp.ui.base.BaseViewHolder
import com.example.awesomeapp.ui.other.ItemEmptyScreenViewModel

class ItemHomeAdapter(private val dataList: MutableList<Photo>) : RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var listener: Listener
    private lateinit var context: Context

    fun setListener(listener: Listener, context: Context) {
        this.listener = listener
        this.context = context
    }

    interface Listener {
        fun itemOnClick(photo: Photo)
    }

    fun addItems(dataList: List<Photo>) {
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        dataList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding = ItemHomeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false)
                ViewHolder(binding)
            }
            else -> {
                val binding = ItemEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false)
                EmptyViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return if (dataList.isNotEmpty()) {
            dataList.size
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList.isNotEmpty()) {
            VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_EMPTY
        }
    }

    inner class ViewHolder(var binding: ItemHomeBinding) : BaseViewHolder(binding.root), ItemHomeViewModel.Listener {
        private lateinit var viewModel: ItemHomeViewModel

        override fun onBind(position: Int) {
            val response: Photo = dataList[position]

            viewModel = ItemHomeViewModel(response, this)
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

        override fun itemOnClick(photo: Photo) {
            listener.itemOnClick(photo)
        }
    }

    inner class EmptyViewHolder(var binding: ItemEmptyBinding) : BaseViewHolder(binding.root) {
        private lateinit var viewModel: ItemEmptyScreenViewModel

        override fun onBind(position: Int) {
            viewModel = ItemEmptyScreenViewModel(context.resources.getString(R.string.empty_label))
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    companion object {
        private val TAG = ItemHomeAdapter::class.java.simpleName

        private const val VIEW_TYPE_EMPTY = 0
        private const val VIEW_TYPE_NORMAL = 1
    }
}
