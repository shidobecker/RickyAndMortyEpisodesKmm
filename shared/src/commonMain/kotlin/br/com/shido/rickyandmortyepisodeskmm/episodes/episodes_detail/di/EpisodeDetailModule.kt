package br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_detail.di

import br.com.shido.rickyandmortyepisodeskmm.episodes.common.usecase.EpisodeDetailUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectEpisodeDetailCommonModule() = loadFeature

val episodesDetailCommonModule = module {
    factory { EpisodeDetailUseCase(get()) }
}


private val loadFeature by lazy {
    loadKoinModules(
        listOf(episodesDetailCommonModule)
    )
}

