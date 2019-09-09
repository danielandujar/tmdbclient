package com.cry.tmdbclient.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.cry.tmdbclient.R
import com.cry.tmdbclient.databinding.ActivityMainBinding
import com.cry.tmdbclient.viewmodels.MainViewModel
import com.cry.tmdbclient.views.Adapters.TabAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {


    private lateinit var viewPager : ViewPager
    private lateinit var bottomNavBar : BottomNavigationView
    private lateinit var adapter : TabAdapter
    private lateinit var viewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        //Not using synthetics because it was reported that
        //it's no-longer a recommended practice
        viewPager = findViewById(R.id.viewPager)
        bottomNavBar = findViewById(R.id.bottomNavView)

        adapter = TabAdapter(supportFragmentManager)

        adapter.addFragment(PopularMoviesFragment(), getString(R.string.popular_movies))
        adapter.addFragment(NewestMoviesFragment(), getString(R.string.recent_movies))
        adapter.addFragment(FavoriteMoviesFragment(), getString(R.string.favorite_movies))
        adapter.addFragment(WatchListFragment(), getString(R.string.watchlist))

        viewPager.adapter = adapter

        bottomNavBar.setOnNavigationItemSelectedListener(this)

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

            override fun onPageSelected( position : Int) {
                val selectedScreen = viewPager.currentItem
                when (selectedScreen)
                {
                    0 -> { bottomNavBar.selectedItemId = R.id.popular_movies  }
                    1 -> { bottomNavBar.selectedItemId = R.id.recent_movies   }
                    2 -> { bottomNavBar.selectedItemId = R.id.favorite_movies }
                    3 -> { bottomNavBar.selectedItemId = R.id.watchlist       }
                }
            }
        })
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.popular_movies -> {
                viewPager.currentItem = 0
            }
            R.id.recent_movies -> {
                viewPager.currentItem = 1
            }
            R.id.favorite_movies -> {
                viewPager.currentItem = 2
            }
            R.id.watchlist -> {
                viewPager.currentItem = 3
            }
        }
        return true
    }

    fun dummy()
    {
    }
}
