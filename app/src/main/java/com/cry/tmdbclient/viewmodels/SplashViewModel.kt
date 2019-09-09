package com.cry.tmdbclient.viewmodels

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cry.tmdbclient.core.arch.BaseViewModel
import com.cry.tmdbclient.models.api.IpConfig.obj.IpData
import com.cry.tmdbclient.models.repositories.IpRepository
import com.cry.tmdbclient.views.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel (val app : Application) : BaseViewModel(app)
{
    private var mCompletedLoading = MutableLiveData<Boolean>()
    val completedLoading : LiveData<Boolean> get() = mCompletedLoading

    init {
        IpRepository().getIpData {
            handleIpData(it)
        }
    }
    fun handleIpData(data : IpData) {
        if (data.ip.isNotBlank())
            loadMainScreen()
        else {

            Toast.makeText(app, "No Internet Connection", Toast.LENGTH_LONG).show()
            GlobalScope.launch {
                delay(500)
                loadMainScreen()
            }
        }
    }
    fun loadMainScreen()
    {
        app.startActivity(Intent(app, MainActivity::class.java))
        mCompletedLoading.postValue(true)
    }
}