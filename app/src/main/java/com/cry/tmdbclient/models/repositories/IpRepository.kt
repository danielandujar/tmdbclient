package com.cry.tmdbclient.models.repositories

import com.cry.tmdbclient.models.api.IpConfig.IpConfigClient
import com.cry.tmdbclient.models.api.IpConfig.obj.IpData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class IpRepository
{
    fun getIpData(onSuccess : (IpData) -> Unit)
    {
        GlobalScope.launch {
            IpConfigClient().loadIpData {
                onSuccess(it)
            }
        }
    }
}