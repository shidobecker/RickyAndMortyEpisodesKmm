package br.com.shido.rickyandmortyepisodeskmm.episodes_list.usecase

import br.com.shido.rickyandmortyepisodeskmm.episodes_list.model.Episode
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.repository.EpisodesRepository
import br.com.shido.rickyandmortyepisodeskmm.exception.ApplicationException
import br.com.shido.rickyandmortyepisodeskmm.exception.Error_Fetching_Episode_Code
import br.com.shido.rickyandmortyepisodeskmm.model.CommonDataState
import br.com.shido.rickyandmortyepisodeskmm.util.CommonFlow
import br.com.shido.rickyandmortyepisodeskmm.util.asCommonFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class EpisodeDetailUseCase(private val repository: EpisodesRepository) {

    fun fetchEpisode(id: String): CommonFlow<CommonDataState<Episode>> = flow {
        emit(CommonDataState.loading())
        repository.fetchEpisode(id).catch {
            emit(
                CommonDataState.error<Episode>(
                    ApplicationException(code = Error_Fetching_Episode_Code, it.message)
                )
            )
        }.collect {
            emit(CommonDataState.data(it))
        }
    }.asCommonFlow()



}