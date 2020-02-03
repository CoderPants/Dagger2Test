package com.testapp.dagger2test.di.main

import androidx.lifecycle.ViewModel
import com.testapp.dagger2test.di.ViewModelKey
import com.testapp.dagger2test.ui.main.posts.PostsViewModel
import com.testapp.dagger2test.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModule(profileViewModel: ProfileViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostViewModule(profileViewModel: PostsViewModel) : ViewModel
}