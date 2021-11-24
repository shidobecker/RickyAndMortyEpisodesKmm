package br.com.shido.rickyandmortyepisodeskmm.android.episode_list.di

import br.com.shido.rickyandmortyepisodeskmm.android.episode_list.viewmodel.EpisodeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectEpisodeListFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModel
        )
    )
}

private val viewModel = module {
    viewModel { EpisodeListViewModel(get()) }
}