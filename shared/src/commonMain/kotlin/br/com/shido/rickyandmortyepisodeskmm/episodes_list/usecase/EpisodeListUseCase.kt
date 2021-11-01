package br.com.shido.rickyandmortyepisodeskmm.episodes_list.usecase

import br.com.shido.rickyandmortyepisodeskmm.episodes_list.constants.EPISODE_LIST_PAGE_SIZE
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.repository.EpisodesRepository
import br.com.shido.rickyandmortyepisodeskmm.exception.ApplicationException
import br.com.shido.rickyandmortyepisodeskmm.exception.Error_Fetching_Episodes_Code
import br.com.shido.rickyandmortyepisodeskmm.model.CommonDataState
import br.com.shido.rickyandmortyepisodeskmm.model.Episode
import br.com.shido.rickyandmortyepisodeskmm.util.CommonFlow
import br.com.shido.rickyandmortyepisodeskmm.util.asCommonFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class EpisodeListUseCase(private val repository: EpisodesRepository) {


    fun fetchEpisodes2(page: Int = 1): CommonFlow<CommonDataState<List<Episode>>> = flow {
        emit(CommonDataState.loading())
        delay(2000)

        repository.fetchEpisodes(page).catch {
            emit(
                CommonDataState.error<List<Episode>>(
                    ApplicationException(
                        code = Error_Fetching_Episodes_Code,
                        it.message
                    )
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