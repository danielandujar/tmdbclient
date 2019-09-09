package com.cry.tmdbclient.views

import android.content.Context
import android.net.Uri
import android.os.Bundle
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
import com.cry.tmdbclient.viewmodels.NewestMoviesViewModel
import com.cry.tmdbclient.views.Adapters.MoviesListAdapter

class NewestMoviesFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var viewModel : NewestMoviesViewModel
    lateinit var lstMovies : RecyclerView
    lateinit var mAdapterObserver : Observer<MoviesListAdapter>
    lateinit var swipeRefresh : SwipeRefreshLayout
    lateinit var progressSpinner : ProgressBar
    lateinit var mLoadingObserver : Observer<Boolean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_newest_movies, container, false)
        viewModel = ViewModelProvider(this.activity!!).get(NewestMoviesViewModel::class.java)
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
        lstMovies = view.findViewById(R.id.lstMovies)
        mAdapterObserver = Observer{
            lstMovies.adapter = it
        }
        viewModel.adapter.observe(this, mAdapterObserver)

        mLoadingObserver = Observer {
            if (!it)
                progressSpinner.visibility = View.GONE
        }
        viewModel.classIsLoading.observe(this, mLoadingObserver)
    }

    override fun onRefresh() {
        updateFavorites()
    }
    private fun updateFavorites(){
        swipeRefresh.isRefreshing = true
        viewModel.fetchNewestMovies{
            activity!!.runOnUiThread {
                swipeRefresh.isRefreshing = false
            }
        }
    }
}
