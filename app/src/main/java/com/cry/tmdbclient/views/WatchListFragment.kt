package com.cry.tmdbclient.views

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.cry.tmdbclient.R
import com.cry.tmdbclient.viewmodels.WatchListViewModel
import com.cry.tmdbclient.views.Adapters.WatchListAdapter

class WatchListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var viewModel : WatchListViewModel
    lateinit var adapterObserver : Observer<WatchListAdapter>
    lateinit var lstMovies : RecyclerView
    lateinit var progressSpinner : ProgressBar
    lateinit var swipeRefresh : SwipeRefreshLayout
    lateinit var mLoadingObserver : Observer<Boolean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_watch_list, container, false)
        try {
            viewModel = ViewModelProvider(this).get(WatchListViewModel::class.java)
        } catch (e : IllegalArgumentException) {
            //I dunno why this specific line is giving me an error...
            Log.wtf("ViewModelState", e.message)
        }
        lstMovies = view.findViewById(R.id.lstMovies)
        progressSpinner = view.findViewById(R.id.progressSpinner)
        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterObserver = Observer {
            lstMovies.adapter = it
        }
        viewModel.adapter.observe(this, adapterObserver)

        mLoadingObserver = Observer {
            if (!it)
                progressSpinner.visibility = View.GONE
        }
        viewModel.classIsLoading.observe(this, mLoadingObserver)
    }

    override fun onRefresh() {
        refreshWatchList()
    }

    private fun refreshWatchList()
    {
        swipeRefresh.isRefreshing = true
        viewModel.queryWatchList {
            activity!!.runOnUiThread{
                swipeRefresh.isRefreshing = false
            }
        }
    }
}
