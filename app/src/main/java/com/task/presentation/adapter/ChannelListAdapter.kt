package com.task.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.R
import com.task.core.domain.model.ChannelList
import com.task.databinding.ChannelItemBinding

class ChannelListAdapter(
    private val context: Context,
    private val channelList: ArrayList<ChannelList>,
) : RecyclerView.Adapter<ChannelListAdapter.ChannelViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateChannelList(newChannelList: ArrayList<ChannelList>) {
        channelList.clear()
        channelList.addAll(newChannelList)
        notifyDataSetChanged()
    }

    inner class ChannelViewHolder(private val binding: ChannelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: ChannelList,
            context: Context,
            channelList: ArrayList<ChannelList>,
        ) {
            binding.tvTitle.text = data.title

            Picasso.get()
                .load(data.pic)
                .fit()
                .placeholder(R.drawable.icon_wait24)
                .error(R.drawable.baseline_error_outline_24)
                .into(binding.channelImage)
            binding.executePendingBindings()

            binding.channelItem.rootView.setOnClickListener {
                val position = adapterPosition
                val title = channelList[position].title
                val image = channelList[position].pic
                val date = channelList[position].date
                val desc = channelList[position].discription
                val bundle = Bundle()
                bundle.putString("title", title)
                bundle.putString("image", image)
                bundle.putString("date", date)
                bundle.putString("desc", desc)
                it.findNavController()
                    .navigate(R.id.detailsFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChannelItemBinding.inflate(inflater, parent, false)
        return ChannelViewHolder(binding)
    }

    override fun getItemCount() = channelList.size

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val data = channelList[position]
        holder.bind(data, context, channelList)
    }


}