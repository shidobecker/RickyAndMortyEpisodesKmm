package br.com.shido.rickyandmortyepisodeskmm.android.episode_list.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_list.events.EpisodeListEvent
import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_list.usecase.EpisodeListUseCase
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.model.Episode
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.model.EpisodeListState


class EpisodeListViewModel(private val useCase: EpisodeListUseCase) : ViewModel() {

    private val _episodesListState: MutableState<EpisodeListState> =
        mutableStateOf(EpisodeListState())
    val episodesState get() = _episodesListState

    private fun loadEpisodes() {
        useCase.fetchEpisodes(_episodesListState.value.page)
            .collectCommon(viewModelScope) { dataState ->
                val currentList = appendEpisodes(dataState.data ?: emptyList())
                _episodesListState.value =
                    _episodesListState.value.copy(
                        isIdle = false,
                        error = dataState.error,
                        episodeList = currentList.toList(),
                        isLoading = dataState.isLoading
                    )

            }
    }

    fun onTriggerEvent(event: EpisodeListEvent) {
        when (event) {
            EpisodeListEvent.LoadEpisodes -> {
                loadEpisodes()
            }
            EpisodeListEvent.NextPage -> {
                nextPage()
            }
        }
    }


    private fun nextPage() {
        _episodesListState.value =
            _episodesListState.value.copy(page = _episodesListState.value.page + 1)
        loadEpisodes()
    }




    fun shouldLoadMoreItems(index: Int): Boolean {
        return useCase.indexGreaterThanPage(
            index,
            _episodesListState.value.page
        ) && _episodesListState.value.isLoading.not()
    }

    private fun appendEpisodes(fetchedList: List<Episode>): MutableList<Episode> {
        val currentList = mutableListOf<Episode>()
        currentList.addAll(_episodesListState.value.episodeList)
        currentList.addAll(fetchedList)
        return currentList
    }



}