package com.testapp.dagger2test.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.testapp.dagger2test.SessionManager
import com.testapp.dagger2test.models.User
import com.testapp.dagger2test.network.auth.AuthApi
import com.testapp.dagger2test.network.auth.AuthResource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel
    @Inject
    constructor(private val authApi: AuthApi,
                private val sessionManager: SessionManager) : ViewModel() {

    private val TAG : String = "AuthViewModelTAG"

    private fun getPublisher(userId : Int) =
        authApi.getUser(userId)
        //Instead calling onError
        .onErrorReturn { User() }
        .map(object : Function<User, AuthResource<User>> {
            override fun apply(user: User): AuthResource<User> =
                if(user.id == -1)
                    AuthResource.Error(null, "Couldn't authenticate")
                else
                    AuthResource.Authenticated(user)

        })
        .subscribeOn(Schedulers.io())

    private fun queryUserId(userId : Int) : LiveData<AuthResource<User>> =
        LiveDataReactiveStreams.fromPublisher(getPublisher(userId))

    fun authenticateWithId(id : Int)  {
        sessionManager.authenticateWithId(queryUserId(id))
        Log.i(TAG, "authenticate with id")
    }

    fun observeAuthState () : LiveData<AuthResource<User>> = sessionManager.getAuthUser()
}