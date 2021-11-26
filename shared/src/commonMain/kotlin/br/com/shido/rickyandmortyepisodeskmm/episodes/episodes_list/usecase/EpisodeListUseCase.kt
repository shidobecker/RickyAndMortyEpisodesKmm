package br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_list.usecase

import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_list.constants.EPISODE_LIST_PAGE_SIZE
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.repository.EpisodesRepository
import br.com.shido.rickyandmortyepisodeskmm.exception.ApplicationException
import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_list.exception.Error_Fetching_Episodes_Code
import br.com.shido.rickyandmortyepisodeskmm.model.CommonDataState
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.model.Episode
import br.com.shido.rickyandmortyepisodeskmm.util.CommonFlow
import br.com.shido.rickyandmortyepisodeskmm.util.asCommonFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class EpisodeListUseCase(private val repository: EpisodesRepository) {

    fun fetchEpisodes(page: Int = 1): CommonFlow<CommonDataState<List<Episode>>> = flow {
        emit(CommonDataState.loading())
        repository.fetchEpisodes(page).catch {
            emit(
                CommonDataState.error<List<Episode>>(
                    ApplicationException(code = Error_Fetching_Episodes_Code, it.message)
                )
            )
        }.collect {
            emit(CommonDataState.data(it))
        }
    }.asCommonFlow()


    fun indexGreaterThanPage(index: Int, page: Int): Boolean {
        return (index + 1) >= (page * EPISODE_LIST_PAGE_SIZE)
    }

}