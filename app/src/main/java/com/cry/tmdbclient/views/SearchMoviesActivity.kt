package com.cry.tmdbclient.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.cry.tmdbclient.R
import com.cry.tmdbclient.databinding.ActivitySearchMoviesBinding
import com.cry.tmdbclient.viewmodels.SearchMoviesViewModel
import com.cry.tmdbclient.views.Adapters.SearchListAdapter

class SearchMoviesActivity : AppCompatActivity() {
    lateinit var viewModel : SearchMoviesViewModel
    lateinit var lstMovies : RecyclerView
    lateinit var btnSearch : ImageButton
    lateinit var toolbarSearchbox : EditText
    lateinit var adapterObserver : Observer<SearchListAdapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivitySearchMoviesBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_movies)
        viewModel = ViewModelProvider(this).get(SearchMoviesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapterObserver = Observer {
            lstMovies.adapter = it
        }
        viewModel.adapter.observe(this, adapterObserver)

        btnSearch = findViewById(R.id.btnSearch)
        toolbarSearchbox = findViewById(R.id.toolbar_searchbox)

        toolbarSearchbox.setOnKeyListener { v, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                performSearch(v)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        lstMovies = findViewById(R.id.lstMovies)
    }
    fun backButtonClicked(view : View)
    {
        finish()
    }
    fun performSearch(view : View )
    {
        viewModel.searchMovie(toolbarSearchbox.text.toString())
    }

}
