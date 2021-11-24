package br.com.shido.rickyandmortyepisodeskmm.android

import android.app.Application
import br.com.shido.rickyandmortyepisodeskmm.android.episode_detail.di.injectEpisodeDetailFeature
import br.com.shido.rickyandmortyepisodeskmm.android.episode_list.di.injectEpisodeListFeature
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.di.injectEpisodeDetailCommonModule
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.di.injectEpisodeListCommonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppClass)
        }
        injectFeatures()
    }

    private fun injectFeatures() {
        injectEpisodeListCommonModule()
        injectEpisodeListFeature()
        injectEpisodeDetailCommonModule()
        injectEpisodeDetailFeature()
    }

}