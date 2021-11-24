package br.com.shido.rickyandmortyepisodeskmm.episodes_list.di

import br.com.shido.rickyandmortyepisodeskmm.apolloclient.ApolloProvider
import br.com.shido.rickyandmortyepisodeskmm.apolloclient.MyLogger
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.datamapper.CharacterDataMapper
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.datamapper.EpisodeDataMapper
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.datasource.EpisodesApollo
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.datasource.EpisodesDataSource
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.repository.EpisodesRepository
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.repository.EpisodesRepositoryImpl
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.usecase.EpisodeListUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectEpisodeListCommonModule() = loadFeature

val episodesCommonModule = module {
    single<EpisodesRepository> { EpisodesRepositoryImpl(get(), get()) }
    factory { EpisodeListUseCase(get()) }
    single<EpisodesDataSource> { EpisodesApollo(get()) }
    factory { CharacterDataMapper() }
    factory { EpisodeDataMapper(get()) }
    single { ApolloProvider(MyLogger()) }
}


private val loadFeature by lazy {
    loadKoinModules(
        listOf(episodesCommonModule)
    )
}

