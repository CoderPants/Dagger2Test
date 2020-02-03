package com.testapp.dagger2test.ui.main.profile

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testapp.dagger2test.R
import com.testapp.dagger2test.models.User
import com.testapp.dagger2test.network.auth.AuthResource
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment(){

    private val TAG : String = "ProfileFragmentTAG"

    @Inject
    lateinit var providerFactory : ViewModelProvider.Factory

    private lateinit var viewModel : ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.profile_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this, providerFactory)
            .get(ProfileViewModel::class.java)

        subscribeObservers()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun subscribeObservers() {
        val authUser : LiveData<AuthResource<User>> = viewModel.getAuthenticatedUser()

        //Cos livecycle of activity is different from the fragment one
        authUser.removeObservers(viewLifecycleOwner)
        authUser.observe(viewLifecycleOwner, Observer {authResource ->
            when(authResource){
                is AuthResource.Authenticated -> setUserData(authResource.data)
                is AuthResource.Error -> setErrorUserData(authResource.message)
            }
        })
    }

    private fun setErrorUserData(message: String?) {
        email.text = message
        username.text = getString(R.string.error_data)
        website.text = getString(R.string.error_data)
    }

    private fun setUserData(data: User?) {
        email.text = data?.email ?: getString(R.string.error_data)
        username.text = data?.userName ?: getString(R.string.error_data)
        website.text = data?.website ?: getString(R.string.error_data)
    }
}