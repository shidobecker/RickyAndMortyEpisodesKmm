package br.com.shido.rickyandmortyepisodeskmm.episodes.common.repository

import br.com.shido.rickyandmortyepisodeskmm.episodes.common.datasource.EpisodesDataSource
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.model.Episode
import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_detail.exception.Error_Fetching_Episode_Code
import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_list.datamapper.EpisodeDataMapper
import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_list.exception.Error_Fetching_Episodes_Code
import br.com.shido.rickyandmortyepisodeskmm.exception.ApplicationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class EpisodesRepositoryImpl(
    private val dataSource: EpisodesDataSource,
    private val dataMapper: EpisodeDataMapper
) : EpisodesRepository {

    override fun fetchEpisodes(page: Int): Flow<List<Episode>> {
        return flow {
            dataSource.fetchEpisodesList(page).catch {
                throw ApplicationException(Error_Fetching_Episodes_Code, it.message)
            }.collect { response ->
                if (response.hasErrors()) {
                    throw ApplicationException(
                        Error_Fetching_Episodes_Code,
                        response.errors?.firstOrNull()?.message
                    )
                } else {
                    val fields =
                        response.data?.episodes?.results?.mapNotNull { it?.fragments?.episodeResultFields }
                    fields?.let {
                        val mapped = dataMapper.toDomainList(it)
                        emit(mapped)
                    }
                }
            }

        }.flowOn(Dispatchers.Default)

    }


    override fun fetchEpisode(id: String): Flow<Episode> {
        return flow {
            dataSource.fetchEpisodeById(id).catch {
                throw ApplicationException(Error_Fetching_Episode_Code, it.message)
            }.collect { response ->
                if (response.hasErrors()) {
                    throw ApplicationException(
                        Error_Fetching_Episode_Code,
                        response.errors?.firstOrNull()?.message
                    )
                } else {
                    val fields = response.data?.episode?.fragments?.episodeResultFields
                    fields?.let {
                        val mapped = dataMapper.toDomain(it)
                        emit(mapped)
                    }
                }
            }

        }.flowOn(Dispatchers.Default)
    }
}