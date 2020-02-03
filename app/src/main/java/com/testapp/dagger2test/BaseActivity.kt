package com.testapp.dagger2test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.testapp.dagger2test.network.auth.AuthResource
import com.testapp.dagger2test.ui.auth.AuthActivity
import com.testapp.dagger2test.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    private val TAG : String = "BaseActivityTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Before subscribtion")
        subscribeObservers()
        Log.i(TAG, "After subscribtion")
    }

    private fun subscribeObservers() =
        sessionManager.getAuthUser().observe(this, Observer { authResource ->
            when(authResource){
                is AuthResource.Loading -> Log.i(TAG, "Loading...")
                is AuthResource.Authenticated -> {
                }
                is AuthResource.LogOut -> {
                    redirectToTheAuthActivity()
                    Toast.makeText(this, "Loged out", Toast.LENGTH_SHORT).show()
                }
                is AuthResource.Error -> {
                    Toast.makeText(this, "Error! Did you enter id from 1 to 10?", Toast.LENGTH_SHORT).show()
                }
            }
        })


    private fun redirectToTheAuthActivity(){
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }



}