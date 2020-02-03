package com.testapp.dagger2test.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.testapp.dagger2test.R
import com.testapp.dagger2test.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {


    @Provides
    fun providesRequestOptions() : RequestOptions =
        RequestOptions
        .placeholderOf(R.drawable.white_background)
        .error(R.drawable.white_background)


    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager =
        Glide
            .with(application)
            .setDefaultRequestOptions(requestOptions)


    @Provides
    fun provideAppDrawable(application: Application) : Drawable =
        ContextCompat.getDrawable(application, R.drawable.logo)!!

    @Singleton
    @Provides
    fun provideRetrofitInstance() : Retrofit =
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}