package br.com.shido.rickyandmortyepisodeskmm.android.episode_detail.di

import br.com.shido.rickyandmortyepisodeskmm.android.episode_detail.viewmodel.EpisodeDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectEpisodeDetailFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModel
        )
    )
}

private val viewModel = module {
    viewModel { EpisodeDetailViewModel(get()) }
}