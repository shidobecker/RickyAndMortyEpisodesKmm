package br.com.shido.rickyandmortyepisodeskmm.android

import android.app.Application
import br.com.shido.rickyandmortyepisodeskmm.android.di.injectEpisodeListFeature
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.di.injectEpisodeListCommonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppClass)
        }
        injectEpisodeListCommonModule()
        injectEpisodeListFeature()
    }

}