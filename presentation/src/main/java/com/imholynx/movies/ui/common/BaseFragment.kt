package com.imholynx.movies.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.imholynx.movies.AndroidApplication

abstract class BaseFragment : Fragment() {

    val androidApplication: AndroidApplication by lazy { activity?.application as AndroidApplication }

    abstract fun layoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId(), container, false)
}