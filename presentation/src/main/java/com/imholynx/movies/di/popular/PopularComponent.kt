package com.imholynx.movies.di.popular

import com.imholynx.movies.ui.list.ListFragment
import dagger.Subcomponent

@Subcomponent(modules = [PopularModule::class])
interface PopularComponent {

    fun inject(listFragment: ListFragment)
}