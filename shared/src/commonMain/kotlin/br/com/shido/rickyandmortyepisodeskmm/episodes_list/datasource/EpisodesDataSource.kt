package br.com.shido.rickyandmortyepisodeskmm.episodes_list.datasource

import br.com.shido.rickyandmortyepisodeskmm.FetchEpisodeByIdQuery
import br.com.shido.rickyandmortyepisodeskmm.FetchEpisodesListQuery
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.flow.Flow

interface EpisodesDataSource {

    fun fetchEpisodesList(page: Int): Flow<Response<FetchEpisodesListQuery.Data>>

    fun fetchEpisodeById(id: String): Flow<Response<FetchEpisodeByIdQuery.Data>>

}