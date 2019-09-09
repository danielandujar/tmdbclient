package com.cry.tmdbclient.viewmodels

import android.app.Application
import android.content.Intent
import com.cry.tmdbclient.core.arch.BaseViewModel
import com.cry.tmdbclient.views.SearchMoviesActivity

class MainViewModel (val app : Application) : BaseViewModel(app)
{
    init {

    }
    fun searchMovies() {
        app.startActivity(Intent(app, SearchMoviesActivity::class.java))
    }
}