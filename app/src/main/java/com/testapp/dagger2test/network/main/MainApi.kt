package com.testapp.dagger2test.network.main

import com.testapp.dagger2test.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    fun getPostForUser(@Query ("userId") userId : Int) : Flowable<List<Post>>
}