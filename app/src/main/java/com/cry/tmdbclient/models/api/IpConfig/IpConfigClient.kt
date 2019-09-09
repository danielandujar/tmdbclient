package com.cry.tmdbclient.models.api.IpConfig

import android.util.Log
import com.cry.tmdbclient.models.api.IpConfig.obj.IpData
import com.google.gson.GsonBuilder

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IpConfigClient
{
    val BASE_URL = "https://ifconfig.co/"

    fun loadIpData(onSuccess : (IpData) -> Unit)
    {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val ipApi = retrofit.create(IpConfigApi::class.java)

        val call = ipApi.loadData("status:open")

        call.enqueue(object : Callback<IpData> {
            override fun onFailure(call: Call<IpData>?, t: Throwable?) {
                onSuccess(IpData("",0,"", false, "", "","", 0.0,0.0))
            }

            override fun onResponse(call: Call<IpData>?, response: Response<IpData>?) {
                onSuccess(response!!.body()!!)
            }

        })
    }
}
