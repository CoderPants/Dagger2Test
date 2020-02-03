package com.testapp.dagger2test.ui.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.testapp.dagger2test.SessionManager
import javax.inject.Inject

class ProfileViewModel
    @Inject
    constructor(private val sessionManager: SessionManager): ViewModel(){

    private val TAG : String = "ProfileViewModelTag"

    fun getAuthenticatedUser() = sessionManager.getAuthUser()
}