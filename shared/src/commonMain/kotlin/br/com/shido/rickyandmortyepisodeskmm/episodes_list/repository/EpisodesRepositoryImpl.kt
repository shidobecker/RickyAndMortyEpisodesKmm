package br.com.shido.rickyandmortyepisodeskmm.episodes_list.repository

import br.com.shido.rickyandmortyepisodeskmm.episodes_list.datamapper.EpisodeDataMapper
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.datasource.EpisodesDataSource
import br.com.shido.rickyandmortyepisodeskmm.exception.ApplicationException
import br.com.shido.rickyandmortyepisodeskmm.exception.Error_Fetching_Episodes_Code
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.model.Episode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class EpisodesRepositoryImpl(
    private val dataSource: EpisodesDataSource,
    private val dataMapper: EpisodeDataMapper
) : EpisodesRepository {

    override fun fetchEpisodes(page: Int): Flow<List<Episode>> {
        return flow{
             dataSource.fetchEpisodesList(page).catch {
                throw ApplicationException(Error_Fetching_Episodes_Code, it.message)
            }.collect { response ->
                if(response.hasErrors()){
                    throw ApplicationException(Error_Fetching_Episodes_Code, response.errors?.firstOrNull()?.message)
                }else{
                    val fields = response.data?.episodes?.results?.mapNotNull { it?.fragments?.episodeResultFields }
                    fields?.let {
                        val mapped = dataMapper.toDomainList(it)
                        emit(mapped)
                    }
                }
            }

        }.flowOn(Dispatchers.Default)

    }
}