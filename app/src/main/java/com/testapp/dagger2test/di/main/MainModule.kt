package com.testapp.dagger2test.di.main

import com.testapp.dagger2test.network.main.MainApi
import com.testapp.dagger2test.ui.main.posts.PostRVAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainAPI(retrofit: Retrofit) : MainApi = retrofit.create(MainApi::class.java)

    @MainScope
    @Provides
    fun providePostRVAdapter() : PostRVAdapter = PostRVAdapter()
}