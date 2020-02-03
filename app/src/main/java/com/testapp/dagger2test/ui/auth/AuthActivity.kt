package com.testapp.dagger2test.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.testapp.dagger2test.BaseActivity
import com.testapp.dagger2test.R
import com.testapp.dagger2test.network.auth.AuthResource
import com.testapp.dagger2test.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject


class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private val TAG : String = "TagAuthActivity"

    private lateinit var userIdEditText : EditText
    private lateinit var  loadingBar : ProgressBar

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AuthViewModel


    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)


        userIdEditText = user_id_input
        loadingBar = progress_bar

        login_button.setOnClickListener(this)

        viewModel = ViewModelProvider(this, providerFactory)
            .get(AuthViewModel::class.java)

        setLogo()

        subscribeObservers()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(
                findViewById<View>(
                    R.id.login_logo
                ) as ImageView
            )
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.login_button -> attemptLogin()
            else -> return
        }
    }

    private fun attemptLogin() {
        if(userIdEditText.text.toString().isNotEmpty())
            viewModel.authenticateWithId(userIdEditText.text.toString().toInt())
        Log.i(TAG, "AttempToLogIn")
    }

    private fun subscribeObservers () =
        viewModel.observeAuthState().observe(this, Observer { authRecourse ->
            when(authRecourse){
                is AuthResource.Loading -> loadingBar.visibility = View.VISIBLE
                is AuthResource.Authenticated -> {
                    loadingBar.visibility = View.GONE

                    loginSuccess()
                }
                is AuthResource.LogOut -> {
                    loadingBar.visibility = View.GONE
                    Log.i(TAG, "In auth activity")
                    Toast.makeText(this, "Loged out", Toast.LENGTH_SHORT).show()
                }
                is AuthResource.Error -> {
                    loadingBar.visibility = View.GONE
                    Toast.makeText(this, "Error! Did you enter id from 1 to 10?", Toast.LENGTH_SHORT).show()
                }
            }

        })

    private fun loginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}