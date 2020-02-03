package com.testapp.dagger2test.di

import androidx.lifecycle.ViewModelProvider
import com.testapp.dagger2test.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}