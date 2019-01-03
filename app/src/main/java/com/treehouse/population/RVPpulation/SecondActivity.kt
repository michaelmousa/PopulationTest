package com.treehouse.population.RVPpulation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.treehouse.population.R
import com.treehouse.population.RV_URL
import kotlinx.android.synthetic.main.second_activity.*
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondActivity: AppCompatActivity {
    constructor()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val adapter = RVadapter()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(RV_URL)
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = retrofitBuilder
            .client(okHttpClient)
            .build()


        val RVclient = retrofit.create(RVservices::class.java)

        rvCountry.layoutManager = LinearLayoutManager(this)
        rvCountry.adapter = adapter

        RVclient.getResults().enqueue(object : Callback<ResponseData>
        {

         override fun onFailure(call: Call<ResponseData>, throwable: Throwable) {}
         override fun onResponse(call: Call<ResponseData>, response: retrofit2.Response<ResponseData>)
         {
                    if (response.isSuccessful) {
                        adapter.setData(response.body()?.popcall ?: emptyList())

                    }
                }



            })

    }

}
