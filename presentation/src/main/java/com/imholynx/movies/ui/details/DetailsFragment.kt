package com.imholynx.movies.ui.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.imholynx.movies.R
import com.imholynx.movies.ui.common.BaseFragment
import kotlinx.android.synthetic.main.detail_movie_view.*
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject

class DetailsFragment : BaseFragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private val args: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var detailsViewModelFactory: DetailsViewModelFactory

    private lateinit var viewModel: DetailsViewModel

    override fun layoutId() = R.layout.details_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        androidApplication.plusDetailsComponent().inject(this)
        viewModel = ViewModelProviders.of(this, detailsViewModelFactory)
            .get(DetailsViewModel::class.java)
        if (savedInstanceState == null) {
            viewModel.getMovie(args.movieId)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load(it.posterPath)
                .into(poster)
            title.text = it.title
            description.text = it.title
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.dataNotAvailable.observe(viewLifecycleOwner, Observer {
            retry_btn.visibility = if (it) View.VISIBLE else View.GONE
            connection_error_tv.visibility = if (it) View.VISIBLE else View.GONE
            content.visibility = if (it) View.GONE else View.VISIBLE
        })
    }

    override fun onDestroy() {
        androidApplication.clearDetailsComponent()
        super.onDestroy()
    }

}
