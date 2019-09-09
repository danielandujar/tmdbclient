package com.cry.tmdbclient.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.cry.tmdbclient.R
import com.cry.tmdbclient.viewmodels.PopularMoviesViewModel
import com.cry.tmdbclient.views.Adapters.MoviesListAdapter
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class PopularMoviesFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var viewModel : PopularMoviesViewModel
    lateinit var lstMovies : RecyclerView
    lateinit var mAdapterObserver : Observer<MoviesListAdapter>
    lateinit var swipeRefresh : SwipeRefreshLayout
    lateinit var progressSpinner : ProgressBar
    lateinit var mLoadingObserver : Observer<Boolean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_popular_movies, container, false)
        viewModel = ViewModelProvider(this.activity!!).get(PopularMoviesViewModel::class.java)
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
        refreshPopularMovies()
    }

    private fun refreshPopularMovies()
    {
        swipeRefresh.isRefreshing = true
        viewModel.fetchPopularMovies {
            activity!!.runOnUiThread{
                swipeRefresh.isRefreshing = false
            }
        }
    }

}
