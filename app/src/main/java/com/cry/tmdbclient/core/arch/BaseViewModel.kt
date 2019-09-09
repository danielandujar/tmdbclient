package com.cry.tmdbclient.core.arch

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel(application : Application) : AndroidViewModel(application)
{
    protected val TAG = this.javaClass.simpleName
}