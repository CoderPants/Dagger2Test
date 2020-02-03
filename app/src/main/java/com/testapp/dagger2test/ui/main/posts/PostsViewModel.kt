package com.testapp.dagger2test.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.testapp.dagger2test.SessionManager
import com.testapp.dagger2test.models.Post
import com.testapp.dagger2test.network.main.MainApi
import com.testapp.dagger2test.network.main.MainResource
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel
    @Inject
    constructor(private val mainApi: MainApi,
                private val sessionManager: SessionManager) : ViewModel(){

    private val TAG : String = "PostViewModelTag"

    private val userPosts : MediatorLiveData<MainResource<List<Post>>> = MediatorLiveData()

    fun observePosts() : LiveData<MainResource<List<Post>>> {
        userPosts.value = MainResource.Loading()

        val source = LiveDataReactiveStreams.fromPublisher(getPublisher())

        userPosts.addSource(source) { mainResource ->
            userPosts.value = mainResource
            userPosts.removeSource(source)
        }

        return userPosts
    }

    private fun getPublisher(): Flowable<MainResource<List<Post>>> =
        mainApi
            .getPostForUser(sessionManager.getAuthUser().value?.data!!.id)
            .onErrorReturn { listOf(Post()) }
            .map(object : Function<List<Post>, MainResource<List<Post>>>{
                override fun apply(posts: List<Post>): MainResource<List<Post>> {
                    if(posts.isNotEmpty()){
                        if(posts[0].id == -1)
                            return MainResource.Error(null, "Something went wrong")

                        return MainResource.Success(posts)
                    }
                    return MainResource.Error(null, "Something went wrong")
                }
            })
            .subscribeOn(Schedulers.io())


}