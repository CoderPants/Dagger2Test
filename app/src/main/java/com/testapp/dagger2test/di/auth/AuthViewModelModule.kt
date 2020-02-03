package com.testapp.dagger2test.di.auth

import androidx.lifecycle.ViewModel
import com.testapp.dagger2test.di.ViewModelKey
import com.testapp.dagger2test.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}