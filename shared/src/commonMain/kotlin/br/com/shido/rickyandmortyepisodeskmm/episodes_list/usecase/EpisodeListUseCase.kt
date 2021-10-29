package br.com.shido.rickyandmortyepisodeskmm.episodes_list.usecase

import br.com.shido.rickyandmortyepisodeskmm.episodes_list.repository.EpisodesRepository
import br.com.shido.rickyandmortyepisodeskmm.model.CommonDataState
import br.com.shido.rickyandmortyepisodeskmm.model.Episode
import br.com.shido.rickyandmortyepisodeskmm.util.CommonFlow
import br.com.shido.rickyandmortyepisodeskmm.util.asCommonFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import okio.IOException

class EpisodeListUseCase(private val repository: EpisodesRepository) {

    fun fetchEpisodes(page: Int = 1): CommonFlow<CommonDataState<List<Episode>>> = flow {
        emit(CommonDataState.loading())
        delay(2000)

        repository.fetchEpisodes(page).catch {
            emit(CommonDataState.error<List<Episode>>(it.message))
        }.collect {
            emit(CommonDataState.data(message = null, it))
        }

    }.asCommonFlow()

}