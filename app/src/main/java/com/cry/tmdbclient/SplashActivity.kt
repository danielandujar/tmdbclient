package com.cry.tmdbclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cry.tmdbclient.viewmodels.SplashViewModel
import com.cry.tmdbclient.views.MainActivity

class SplashActivity : AppCompatActivity() {

    lateinit var loadingObserver : Observer<Boolean>
    lateinit var viewModel : SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        loadingObserver = Observer{
            if (it) {
                dismissSplash()
            }
        }
        viewModel.completedLoading.observe(this, loadingObserver)
    }

    private fun dismissSplash()
    {
        finish()
    }
}
