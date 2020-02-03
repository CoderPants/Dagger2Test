package com.testapp.dagger2test.di.main

import com.testapp.dagger2test.ui.main.posts.PostFragment
import com.testapp.dagger2test.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment() : ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment() : PostFragment
}