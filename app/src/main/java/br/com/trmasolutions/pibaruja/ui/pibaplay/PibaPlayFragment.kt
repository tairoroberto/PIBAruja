package br.com.trmasolutions.pibaruja.ui.pibaplay

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.transition.ChangeBounds
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.trmasolutions.pibaruja.R
import br.com.trmasolutions.pibaruja.model.YouTubeVideo
import br.com.trmasolutions.pibaruja.ui.home.DetailActivity
import br.com.trmasolutions.pibaruja.utils.extension.showProgress
import br.com.trmasolutions.pibaruja.viewmodel.PibaPlayViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class PibaPlayFragment : Fragment() {

    companion object {
        fun newInstance(titleTab: String): PibaPlayFragment {
            val fragment = PibaPlayFragment()
            fragment.arguments?.putString("title", titleTab)
            return fragment
        }
    }

    private lateinit var viewModel: PibaPlayViewModel
    private lateinit var adapter: PibaPlayRecyclerAdapter
    private val list: MutableList<YouTubeVideo> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setAnimation()
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PibaPlayViewModel::class.java)
        adapter = PibaPlayRecyclerAdapter(list, this::click)
        setRecyclerViewListJobs()

        viewModel.getYouTubeVideos()

        viewModel.getEventResponse().observe(this, Observer {
            adapter.update(it?.items)
            swipeRefreshLayout?.isRefreshing = false
        })

        viewModel.getLoadingStatus().observe(this, Observer {
            activity?.showProgress(recyclerView, progressBar, it == true)
        })
    }

    private fun setRecyclerViewListJobs() {
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener { viewModel.getYouTubeVideos() }
    }

    private fun click(youTubeVideo: YouTubeVideo, imageView: ImageView) {
        val options: ActivityOptionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(context as Activity, Pair.create(imageView, "image"))

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("youTubeVideo", youTubeVideo)
        activity?.startActivity(intent, options.toBundle())
    }

    private fun setAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000
            activity?.window?.sharedElementExitTransition = changeBounds
        }
    }
}
