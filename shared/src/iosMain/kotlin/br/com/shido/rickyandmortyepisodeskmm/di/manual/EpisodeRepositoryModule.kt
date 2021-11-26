package br.com.shido.rickyandmortyepisodeskmm.di.manual

import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_list.datamapper.EpisodeDataMapper
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.datasource.EpisodesDataSource
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.repository.EpisodesRepository
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.repository.EpisodesRepositoryImpl

class EpisodeRepositoryModule(
    private val dataSource: EpisodesDataSource,
    private val dataMapper: EpisodeDataMapper
) {

    val episodeCache: EpisodesRepository by lazy {
        EpisodesRepositoryImpl(dataSource, dataMapper)
    }

}