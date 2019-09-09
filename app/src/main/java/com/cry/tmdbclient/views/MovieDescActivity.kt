package com.cry.tmdbclient.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cry.tmdbclient.R
import com.cry.tmdbclient.core.utils.Constants
import com.cry.tmdbclient.databinding.ActivityMovieDescBinding
import com.cry.tmdbclient.models.database.obj.Actor
import com.cry.tmdbclient.viewmodels.MovieDescViewModel
import com.cry.tmdbclient.views.Adapters.ActorsListAdapter

//import com.cry.tmdbclient.databinding.ActivityMovieDescBinding

class MovieDescActivity : AppCompatActivity() {
    lateinit var viewModel : MovieDescViewModel
    lateinit var imgPoster : ImageView
    lateinit var imgBackdrop : ImageView
    lateinit var imgStar1 : ImageView
    lateinit var imgStar2 : ImageView
    lateinit var imgStar3 : ImageView
    lateinit var imgStar4 : ImageView
    lateinit var imgStar5 : ImageView
    lateinit var txtTitle : TextView
    lateinit var txtTagline : TextView
    lateinit var txtOverview : TextView
    lateinit var txtBudget : TextView
    lateinit var txtRevenue : TextView
    lateinit var txtReleaseDate : TextView
    lateinit var txtStatus : TextView
    lateinit var txtIsAdult : TextView
    lateinit var txtVoteAvg : TextView
    lateinit var txtVoteCount : TextView
    lateinit var lstActors : RecyclerView
    lateinit var btnAddFavorite : Button
    lateinit var btnAddWatchList : Button

    lateinit var backdropObserver : Observer<String>
    lateinit var posterObserver : Observer<String>
    lateinit var voteAverageObserver : Observer<Double>
    lateinit var actorsObserver : Observer<ActorsListAdapter>
    lateinit var actorsAdapterObserver : Observer<ActorsListAdapter>
    lateinit var isFavoriteObserver : Observer<Boolean>
    lateinit var watchListObserver : Observer<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_movie_desc)
        var binding : ActivityMovieDescBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_desc)

        viewModel = ViewModelProvider(this).get(MovieDescViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val movieId = intent.getIntExtra("MOVIE_ID", 0)

        setupViews()

        setupObservers()

        viewModel.loadMovie(movieId)

    }
    private fun setupViews() {
        //imgPoster = findViewById(R.id.imgPoster)
        imgBackdrop = findViewById(R.id.imvBackDrop)
        imgStar1 = findViewById(R.id.imvStar1)
        imgStar2 = findViewById(R.id.imvStar2)
        imgStar3 = findViewById(R.id.imvStar3)
        imgStar4 = findViewById(R.id.imvStar4)
        imgStar5 = findViewById(R.id.imvStar5)
        txtTitle = findViewById(R.id.txtMovieTitle)
        txtTagline = findViewById(R.id.txtMovieSubtitle)
        txtOverview = findViewById(R.id.txtOverview)
        txtBudget = findViewById(R.id.txtBudget)
        txtRevenue = findViewById(R.id.txtRevenue)
        txtReleaseDate =findViewById(R.id.txtReleaseDate)
        txtStatus = findViewById(R.id.txtStatus)
        txtIsAdult = findViewById(R.id.txtAdult)
        txtVoteAvg = findViewById(R.id.txtVoteAverage)
        txtVoteCount = findViewById(R.id.txtVoteCount)
        lstActors = findViewById(R.id.lstActors)
        btnAddFavorite = findViewById(R.id.btnAddFavorite)
        btnAddWatchList = findViewById(R.id.btnAddWatchList)
    }
    private fun setupObservers(){
        backdropObserver = Observer {
            loadImage(it, imgBackdrop)
        }
        viewModel.backdropPath.observe(this, backdropObserver)

        /*
        posterObserver = Observer {
            loadImage(it, imgPoster)
        }
        viewModel.posterPath.observe(this, posterObserver)
        */
        actorsObserver = Observer {
            lstActors.adapter = it
        }
        viewModel.adapter.observe(this, actorsObserver)

        voteAverageObserver = Observer {
            setupStars(it)
        }
        viewModel.voteAverage.observe(this, voteAverageObserver)

        actorsAdapterObserver = Observer {
            lstActors.adapter = it
        }
        viewModel.adapter.observe(this, actorsAdapterObserver)

        isFavoriteObserver = Observer {
            if (it) {
                btnAddFavorite.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.ic_favorite_filled_24dp), null, null, null)
                btnAddFavorite.text = getString(R.string.remove_favorites)
            } else {
                btnAddFavorite.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.ic_favorite_outline_24dp), null, null, null)
                btnAddFavorite.text = getString(R.string.add_favorites)
            }
        }
        viewModel.isFavorite.observe(this, isFavoriteObserver)

        watchListObserver = Observer {
            if (it) {
                btnAddWatchList.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.ic_remove_24dp), null, null, null)
                btnAddWatchList.text = getString(R.string.remove_watchlist)
            } else {
                btnAddWatchList.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.ic_list_24dp), null, null, null)
                btnAddWatchList.text = getString(R.string.add_watchlist)
            }
        }
        viewModel.isInWatchList.observe(this, watchListObserver)
    }
    private fun setupStars(rIdx : Double) {
        //1st Star
        if (rIdx > 1.0) {
            imgStar1.setImageDrawable(resources.getDrawable(R.drawable.ic_star_24dp))
        }
        else if (rIdx > 0) {
            imgStar1.setImageDrawable(resources.getDrawable(R.drawable.ic_star_half_24dp))
        }

        //2nd Star
        if (rIdx > 2.75) {
            imgStar2.setImageDrawable(resources.getDrawable(R.drawable.ic_star_24dp))
        }
        else if (rIdx > 1.75) {
            imgStar2.setImageDrawable(resources.getDrawable(R.drawable.ic_star_half_24dp))
        }

        //3rd Star
        if (rIdx > 4.75) {
            imgStar3.setImageDrawable(resources.getDrawable(R.drawable.ic_star_24dp))
        }
        else if (rIdx > 3.75) {
            imgStar3.setImageDrawable(resources.getDrawable(R.drawable.ic_star_half_24dp))
        }

        //4th Star
        if (rIdx > 6.75) {
            imgStar4.setImageDrawable(resources.getDrawable(R.drawable.ic_star_24dp))
        }
        else if (rIdx > 5.75) {
            imgStar4.setImageDrawable(resources.getDrawable(R.drawable.ic_star_half_24dp))
        }

        //5th Star
        if (rIdx > 9) {
            imgStar5.setImageDrawable(resources.getDrawable(R.drawable.ic_star_24dp))
        }
        else if (rIdx > 7.75) {
            imgStar5.setImageDrawable(resources.getDrawable(R.drawable.ic_star_half_24dp))
        }
    }
    private fun loadImage(sourcePath : String, imv : ImageView) {
        val defaultOptions = RequestOptions()

        Glide.with(this)
            .setDefaultRequestOptions(defaultOptions)
            .load(Constants.TMDB_IMG_URL + sourcePath)
            .into(imv)
    }
}
