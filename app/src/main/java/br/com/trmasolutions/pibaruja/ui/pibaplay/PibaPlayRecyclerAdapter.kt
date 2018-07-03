package br.com.trmasolutions.pibaruja.ui.pibaplay

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.trmasolutions.pibaruja.R
import br.com.trmasolutions.pibaruja.model.Event
import br.com.trmasolutions.pibaruja.model.YouTubeVideo
import br.com.trmasolutions.pibaruja.utils.extension.loadImage


class PibaPlayRecyclerAdapter(private var list: MutableList<YouTubeVideo>,
                              private val onClick: (youTubeVideo: YouTubeVideo, imageView: ImageView) -> Unit) : RecyclerView.Adapter<PibaPlayRecyclerAdapter.ViewHolder>() {

    private var lastPosition = -1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick(item, holder.imageView)
        }
        setAnimation(holder.itemView, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > 0) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewLogo)
        private val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        private val textViewDescription: TextView = view.findViewById(R.id.textViewDescription)
        private val progressImage: ProgressBar = view.findViewById(R.id.progressImage)

        fun bind(youTubeVideo: YouTubeVideo) {
            if (!youTubeVideo.snippet.thumbnails.default.url.isEmpty()) {
                imageView.loadImage(youTubeVideo.snippet.thumbnails.default.url, progressImage)
            }

            textViewTitle.text = youTubeVideo.snippet.channelTitle
            textViewDescription.text = youTubeVideo.snippet.description
        }
    }

    fun update(items: List<YouTubeVideo>?) {
        this.list.clear()
        if (items != null) {
            this.list.addAll(items)
        }
        notifyDataSetChanged()
    }
}