package br.com.shido.rickyandmortyepisodeskmm.di.koin

import br.com.shido.rickyandmortyepisodeskmm.episodes_list.di.episodesCommonModule
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.usecase.EpisodeListUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initIosEpisodeDependencies() = startKoin {
    modules(episodesCommonModule, iosModule)
}

private val iosModule = module {
    factory { EpisodeListUseCase(get()) }

}

/**
 * This is a DI Component exposed for our Swift code. It contains all the business classes
 * that matter for the iOS app.
 */
class IosEpisodesComponent : KoinComponent {
    fun provideEpisodesUseCase(): EpisodeListUseCase = get()
}