package com.example.tasks.request

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("api/breeds/image/random")
    fun randomImage(): Single<RequestResult>
}