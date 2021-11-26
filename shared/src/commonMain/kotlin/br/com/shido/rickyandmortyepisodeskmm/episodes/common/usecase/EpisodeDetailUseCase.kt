package br.com.shido.rickyandmortyepisodeskmm.episodes.common.usecase

import br.com.shido.rickyandmortyepisodeskmm.episodes.common.model.Episode
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.repository.EpisodesRepository
import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_detail.exception.Error_Fetching_Episode_Code
import br.com.shido.rickyandmortyepisodeskmm.exception.ApplicationException
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