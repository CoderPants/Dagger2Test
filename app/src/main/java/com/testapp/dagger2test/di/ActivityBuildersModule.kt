package com.testapp.dagger2test.di

import com.testapp.dagger2test.di.auth.AuthModule
import com.testapp.dagger2test.di.auth.AuthScope
import com.testapp.dagger2test.di.auth.AuthViewModelModule
import com.testapp.dagger2test.di.main.MainFragmentBuildersModule
import com.testapp.dagger2test.di.main.MainModule
import com.testapp.dagger2test.di.main.MainScope
import com.testapp.dagger2test.di.main.MainViewModelModule
import com.testapp.dagger2test.ui.auth.AuthActivity
import com.testapp.dagger2test.ui.main.MainActivity
import com.testapp.dagger2test.ui.main.profile.ProfileFragment
import com.testapp.dagger2test.ui.main.profile.ProfileViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    //Life cycle is the same as in the auth activity
    @ContributesAndroidInjector(
        modules =
        [AuthViewModelModule::class,
        AuthModule::class]
    )
    abstract fun contributeAuthActivity() : AuthActivity


    @MainScope
    @ContributesAndroidInjector(
        modules = [
        MainFragmentBuildersModule::class,
        MainViewModelModule::class,
        MainModule::class]
    )
    abstract fun contributeMainActivity() : MainActivity
}

