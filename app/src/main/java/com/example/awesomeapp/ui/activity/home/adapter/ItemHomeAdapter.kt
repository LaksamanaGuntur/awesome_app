package com.example.awesomeapp.ui.activity.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.databinding.ItemHomeGridBinding
import com.example.awesomeapp.databinding.ItemHomeListBinding
import com.example.awesomeapp.ui.base.BaseViewHolder

class ItemHomeAdapter(private val dataList: MutableList<Photo>) : RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var listener: Listener
    private lateinit var gridLayoutManager: GridLayoutManager

    fun setListener(listener: Listener, gridLayoutManager: GridLayoutManager) {
        this.listener = listener
        this.gridLayoutManager = gridLayoutManager
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
            VIEW_TYPE_LIST -> {
                val binding = ItemHomeListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false)
                ViewHolderList(binding)
            }
            else -> {
                val binding = ItemHomeGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false)
                ViewHolderGrid(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (gridLayoutManager.spanCount == SPAN_COUNT_LIST) {
            VIEW_TYPE_LIST
        } else {
            VIEW_TYPE_GRID
        }
    }

    inner class ViewHolderList(var binding: ItemHomeListBinding) : BaseViewHolder(binding.root), ItemHomeViewModel.Listener {
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

    inner class ViewHolderGrid(var binding: ItemHomeGridBinding) : BaseViewHolder(binding.root), ItemHomeViewModel.Listener {
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

    companion object {
        private val TAG = ItemHomeAdapter::class.java.simpleName

        const val SPAN_COUNT_GRID = 2
        const val SPAN_COUNT_LIST = 1

        private const val VIEW_TYPE_GRID = 1
        private const val VIEW_TYPE_LIST = 2
    }
}