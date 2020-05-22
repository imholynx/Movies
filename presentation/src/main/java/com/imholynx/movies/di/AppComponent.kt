package com.imholynx.movies.di

import com.imholynx.movies.di.common.AppModule
import com.imholynx.movies.di.common.DataModule
import com.imholynx.movies.di.common.NetworkModule
import com.imholynx.movies.di.popular.PopularComponent
import com.imholynx.movies.di.popular.PopularModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    fun plus(popularModule: PopularModule): PopularComponent
}