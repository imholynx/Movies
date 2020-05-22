package com.imholynx.movies.di

import com.imholynx.movies.di.modules.DataModule
import com.imholynx.movies.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppComponent::class,
        NetworkModule::class,
        DataModule::class
    ]
)
interface AppComponent {

}