package com.treehouse.population

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.treehouse.population.RVPpulation.RVadapter
import com.treehouse.population.RVPpulation.ResponseData
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RVadapter()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofitBuilder = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        val retrofit = retrofitBuilder.client(okHttpClient).build()



        val populitionCharacter = retrofit.create(PopulitionServices::class.java)


        btnViewList.setOnClickListener{
               populitionCharacter.getCountryDetails(country = String(), age = Int)
                   .enqueue(object : Callback<ResponseData>
                {
         override fun onFailure(call: retrofit2.Call<ResponseData>, t: Throwable) {}
         override fun onResponse(call: retrofit2.Call<ResponseData>, response: retrofit2.Response<ResponseData>)
         {

             if (response.isSuccessful)
             {
                adapter.setData(
                    response.body()?.popcall
                        ?: emptyList())

             }


         }


                })
         }
    }
}
