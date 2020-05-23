package com.imholynx.movies.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.imholynx.movies.AndroidApplication
import com.imholynx.movies.R
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var listViewModelFactory: ListViewModelFactory

    private lateinit var viewModel: ListViewModel

    @BindView(R.id.message)
    lateinit var message: TextView

    companion object {
        fun newInstance() = ListFragment()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as AndroidApplication).plusPopularComponent().inject(this)
        viewModel = ViewModelProviders.of(this, listViewModelFactory)
            .get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as AndroidApplication).clearPopularComponent()
    }

}
