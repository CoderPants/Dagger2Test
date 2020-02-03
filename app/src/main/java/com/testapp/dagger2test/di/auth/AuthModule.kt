package com.testapp.dagger2test.di.auth

import com.testapp.dagger2test.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class AuthModule {

    @AuthScope
    //We can use retrofit, 'cos we've added authmodule to the auth scope in authviewmodelmodule class
    @Provides
    fun provideAuthApi(retrofit: Retrofit) : AuthApi = retrofit.create(AuthApi::class.java)
}