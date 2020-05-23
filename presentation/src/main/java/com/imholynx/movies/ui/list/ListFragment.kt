package com.imholynx.movies.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import com.imholynx.movies.AndroidApplication
import com.imholynx.movies.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var listViewModelFactory: ListViewModelFactory

    private lateinit var viewModel: ListViewModel
    private lateinit var groupAdapter: GroupAdapter<GroupieViewHolder>

    companion object {
        fun newInstance() = ListFragment()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as AndroidApplication).plusPopularComponent().inject(this)
        viewModel = ViewModelProviders.of(this, listViewModelFactory)
            .get(ListViewModel::class.java)
        groupAdapter = GroupAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            groupAdapter.update(it.map { movie -> MovieItem(movie.title, movie.posterPath) })
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
        (activity?.application as AndroidApplication).clearPopularComponent()
    }

}
