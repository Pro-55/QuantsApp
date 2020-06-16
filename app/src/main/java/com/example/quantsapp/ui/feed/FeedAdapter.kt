package com.example.quantsapp.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.quantsapp.R
import com.example.quantsapp.models.FeedItem
import com.example.quantsapp.util.extensions.formatTieStamp
import com.example.quantsapp.util.extensions.gone
import com.example.quantsapp.util.extensions.visible
import kotlinx.android.synthetic.main.layout_feed_item.view.*

class FeedAdapter(private val glide: RequestManager) :
    ListAdapter<FeedItem, FeedAdapter.ViewHolder>(FeedItemDC()) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_feed_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<FeedItem>) = submitList(data.toMutableList())

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(feedItem: FeedItem) = with(itemView) {

            glide.load(feedItem.profilePic)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(CircleCrop())
                .placeholder(resources.getColor(R.color.color_secondary_text))
                .into(img_profile)

            img_cover.apply {
                val image = feedItem.image
                if (!image.isNullOrEmpty()) {
                    visible()
                    glide.load(feedItem.image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(resources.getColor(R.color.color_secondary_text))
                        .into(this)
                } else gone()
            }

            txt_name.text = feedItem.name

            txt_time_stamp.text = feedItem.timeStamp.formatTieStamp()

            txt_status.apply {
                val status = feedItem.status.trim()
                if (status.isNotEmpty()) {
                    visible()
                    text = status
                } else gone()
            }

            setOnClickListener { listener?.onClick(feedItem) }

        }

    }


    private class FeedItemDC : DiffUtil.ItemCallback<FeedItem>() {
        override fun areItemsTheSame(
            oldItem: FeedItem,
            newItem: FeedItem
        ): Boolean = oldItem._id == newItem._id

        override fun areContentsTheSame(
            oldItem: FeedItem,
            newItem: FeedItem
        ): Boolean = oldItem == newItem
    }

    interface Listener {
        fun onClick(feedItem: FeedItem)
    }

}
