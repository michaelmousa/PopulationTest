package com.treehouse.population.RVPpulation

import com.treehouse.population.RV_URL
import retrofit2.Call
import retrofit2.http.GET

interface RVservices {

    @GET(RV_URL)

    fun getResults(): Call<ResponseData>
}
