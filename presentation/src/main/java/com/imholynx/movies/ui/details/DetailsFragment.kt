package com.imholynx.movies.ui.details

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.imholynx.movies.R
import com.imholynx.movies.ui.common.BaseFragment

class DetailsFragment : BaseFragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel

    override fun layoutId() = R.layout.details_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        androidApplication.plusDetailsComponent().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroy() {
        androidApplication.clearDetailsComponent()
        super.onDestroy()
    }

}
