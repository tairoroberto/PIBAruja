package br.com.trmasolutions.pibaruja.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.trmasolutions.pibaruja.R

class HomeFragment : Fragment() {

    companion object {
        fun newInstance(titleTab: String): HomeFragment {
            val fragment = HomeFragment()
            fragment.arguments?.putString("title", titleTab)
            return fragment
        }
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.getEvents()

        viewModel.getEventResponse().observe(this, Observer {

        })

        viewModel.getLoadingStatus().observe(this, Observer {

        })
    }
}
