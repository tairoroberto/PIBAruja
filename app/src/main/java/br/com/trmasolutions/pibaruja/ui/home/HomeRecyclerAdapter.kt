package br.com.trmasolutions.pibaruja.ui.home

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
import br.com.trmasolutions.pibaruja.utils.extension.loadImage


class HomeRecyclerAdapter(private var list: MutableList<Event>,
                          private val onClick: (event: Event, imageView: ImageView) -> Unit) : RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

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

        fun bind(event: Event) {
            if (!event.image.isBlank()) {
                imageView.loadImage(event.image, progressImage)
            }

            textViewTitle.text = event.name
            textViewDescription.text = event.description
        }
    }

    fun update(items: List<Event>?) {
        this.list.clear()
        if (items != null) {
            this.list.addAll(items)
        }
        notifyDataSetChanged()
    }
}