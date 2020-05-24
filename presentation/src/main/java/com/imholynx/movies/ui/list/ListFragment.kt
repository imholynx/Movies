package com.imholynx.movies.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.imholynx.movies.R
import com.imholynx.movies.ui.common.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject

class ListFragment : BaseFragment() {

    @Inject
    lateinit var listViewModelFactory: ListViewModelFactory

    private lateinit var viewModel: ListViewModel
    private lateinit var groupAdapter: GroupAdapter<GroupieViewHolder>

    companion object {
        fun newInstance() = ListFragment()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        androidApplication.plusPopularComponent().inject(this)
        viewModel = ViewModelProviders.of(this, listViewModelFactory)
            .get(ListViewModel::class.java)
        groupAdapter = GroupAdapter()
        groupAdapter.setOnItemClickListener { item, view ->
            run {
                val action =
                    ListFragmentDirections.actionListFragmentToDetailsFragment((item as MovieItem).movieId)
                findNavController().navigate(action)
            }
        }
    }

    override fun layoutId() = R.layout.list_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            groupAdapter.update(it.map { movie ->
                MovieItem(
                    movie.id,
                    movie.title,
                    movie.posterPath
                )
            })
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.dataNotAvailable.observe(viewLifecycleOwner, Observer {
            retry_btn.visibility = if (it) View.VISIBLE else View.GONE
            connection_error_tv.visibility = if (it) View.VISIBLE else View.GONE
            recycler_view.visibility = if (it) View.GONE else View.VISIBLE
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retry_btn.setOnClickListener { viewModel.onRetry() }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recycler_view.adapter = groupAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        androidApplication.clearPopularComponent()
    }

}
