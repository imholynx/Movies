package com.imholynx.movies

import android.app.Application
import com.imholynx.movies.di.AppComponent
import com.imholynx.movies.di.DaggerAppComponent
import com.imholynx.movies.di.common.AppModule
import com.imholynx.movies.di.common.DataModule
import com.imholynx.movies.di.common.NetworkModule
import com.imholynx.movies.di.popular.PopularComponent
import com.imholynx.movies.di.popular.PopularModule

class AndroidApplication : Application() {

    private lateinit var appComponent: AppComponent
    private var popularComponent: PopularComponent? = null

    override fun onCreate() {
        super.onCreate()
        initDagger();
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = applicationContext))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .dataModule(DataModule())
            .build()
    }

    fun plusPopularComponent(): PopularComponent {
        popularComponent = appComponent.plus(PopularModule())
        return popularComponent!!
    }

    fun clearPopularComponent() {
        popularComponent = null
    }


}