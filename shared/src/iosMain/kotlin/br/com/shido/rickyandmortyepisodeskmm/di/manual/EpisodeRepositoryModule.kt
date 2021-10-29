package br.com.shido.rickyandmortyepisodeskmm.di.manual

import br.com.shido.rickyandmortyepisodeskmm.datamapper.EpisodeDataMapper
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.datasource.EpisodesDataSource
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.repository.EpisodesRepository
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.repository.EpisodesRepositoryImpl

class EpisodeRepositoryModule(
    private val dataSource: EpisodesDataSource,
    private val dataMapper: EpisodeDataMapper
) {

    val episodeCache: EpisodesRepository by lazy {
        EpisodesRepositoryImpl(dataSource, dataMapper)
    }

}