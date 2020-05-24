package com.imholynx.movies

import android.app.Application
import com.facebook.stetho.Stetho
import com.imholynx.movies.di.AppComponent
import com.imholynx.movies.di.DaggerAppComponent
import com.imholynx.movies.di.common.AppModule
import com.imholynx.movies.di.common.DataModule
import com.imholynx.movies.di.common.NetworkModule
import com.imholynx.movies.di.details.DetailsComponent
import com.imholynx.movies.di.details.DetailsModule
import com.imholynx.movies.di.popular.PopularComponent
import com.imholynx.movies.di.popular.PopularModule

class AndroidApplication : Application() {

    private val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.builder()
            .appModule(AppModule(context = applicationContext))
            .networkModule(NetworkModule(BuildConfig.BASE_URL, BuildConfig.API_KEY))
            .dataModule(DataModule())
            .build()
    }

    private var popularComponent: PopularComponent? = null
    private var detailsComponent: DetailsComponent? = null

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    fun plusPopularComponent(): PopularComponent {
        popularComponent = appComponent.plus(PopularModule())
        return popularComponent!!
    }

    fun clearPopularComponent() {
        popularComponent = null
    }

    fun plusDetailsComponent(): DetailsComponent {
        detailsComponent = appComponent.plus(DetailsModule())
        return detailsComponent!!
    }

    fun clearDetailsComponent() {
        detailsComponent = null
    }

}