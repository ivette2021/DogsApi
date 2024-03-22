package com.ihiviko.dogsapi.Model.Remote

import com.ihiviko.dogsapi.Model.Remote.FromInternet.DogDetail
import com.ihiviko.dogsapi.Model.Remote.FromInternet.Dogs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breeds/list/all")
    suspend fun getBreedsList(): Response<Dogs>

    @GET("breed/{breed}/images")
    suspend fun getImagesList(@Path("breed") breed: String): Response<DogDetail>

}