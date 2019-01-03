package com.treehouse.population

import com.treehouse.population.RVPpulation.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PopulitionServices {

    @GET("populition/{country}/{age}/")
    fun getCountryDetails(@Path("{country}") country: String,
                          @Path("{age}") age: Int.Companion
    ): Call<ResponseData>

}
