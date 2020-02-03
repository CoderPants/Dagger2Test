package com.testapp.dagger2test

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.testapp.dagger2test.models.User
import com.testapp.dagger2test.network.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionManager
@Inject constructor() {

    private val TAG = "SessionManager"
    // data
    private val cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        cachedUser.value = AuthResource.Loading()
        Log.i(TAG, "Outside SessionManager Loop")
        cachedUser.addSource(source) { authResource ->
            Log.i(TAG, "In SessionManager Loop ${authResource is AuthResource.Authenticated}")
            cachedUser.value = authResource
            cachedUser.removeSource(source)
        }
    }

    fun logOut() {
        Log.d(TAG, "logOut: logging out...")
        cachedUser.value = AuthResource.LogOut()
    }


    fun getAuthUser() : LiveData<AuthResource<User>> = cachedUser

}