package com.testapp.dagger2test.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.testapp.dagger2test.R
import com.testapp.dagger2test.network.main.MainResource
import com.testapp.dagger2test.util.VerticalSpaceItemDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.post_fragment.*
import javax.inject.Inject

class PostFragment : DaggerFragment() {

    private val TAG : String = "PostFragmentTag"

    private lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var viewModelProviderFactory : ViewModelProvider.Factory

    @Inject
    lateinit var rvAdapter: PostRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.post_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(PostsViewModel::class.java)

        initRV()

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, Observer {mainResource ->
            when(mainResource){
                is MainResource.Success ->
                    if(mainResource.data != null) rvAdapter.setPosts(posts = mainResource.data)
                is MainResource.Error ->
                   Log.i(TAG, "ERROR")
                is MainResource.Loading ->
                    Log.i(TAG, "Loading...")

            }
        })
    }

    private fun initRV(){
        val recyclerView = recycler_view

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration(15))
        recyclerView.adapter = rvAdapter
    }
}