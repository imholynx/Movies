package com.imholynx.movies.di.details

import com.imholynx.movies.ui.details.DetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    fun inject(detailsFragment: DetailsFragment)
}