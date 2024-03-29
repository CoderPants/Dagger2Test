package com.testapp.dagger2test.network.auth

import com.testapp.dagger2test.models.User
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id : Int) : Flowable<User>
}