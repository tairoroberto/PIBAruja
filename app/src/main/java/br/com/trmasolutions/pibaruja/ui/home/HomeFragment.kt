package br.com.trmasolutions.pibaruja.ui.home

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
import br.com.trmasolutions.pibaruja.model.Event
import br.com.trmasolutions.pibaruja.utils.extension.showProgress
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance(titleTab: String): HomeFragment {
            val fragment = HomeFragment()
            fragment.arguments?.putString("title", titleTab)
            return fragment
        }
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeRecyclerAdapter
    private val list: MutableList<Event> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setAnimation()
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        adapter = HomeRecyclerAdapter(list, this::click)
        setRecyclerViewListJobs()

        viewModel.getEvents()

        viewModel.getEventResponse().observe(this, Observer {
            adapter.update(it?.events)
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
        swipeRefreshLayout.setOnRefreshListener { viewModel.getEvents() }
    }

    private fun click(event: Event, imageView: ImageView) {
        val options: ActivityOptionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(context as Activity, Pair.create(imageView, "image"))

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("event", event)
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
