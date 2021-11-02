package br.com.shido.rickyandmortyepisodeskmm.android.episode_list.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.shido.rickyandmortyepisodeskmm.android.extensions.w
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.events.EpisodeListEvents
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.usecase.EpisodeListUseCase
import br.com.shido.rickyandmortyepisodeskmm.model.Episode
import br.com.shido.rickyandmortyepisodeskmm.model.EpisodeListState
import android.graphics.drawable.Drawable




class EpisodeListViewModel(private val useCase: EpisodeListUseCase) : ViewModel() {

    private val _episodesListState: MutableState<EpisodeListState> =
        mutableStateOf(EpisodeListState())
    val episodesState get() = _episodesListState


    fun onTriggerEvent(event: EpisodeListEvents) {
        when (event) {
            EpisodeListEvents.LoadEpisodes -> {
                loadEpisodes()
            }
            EpisodeListEvents.NextPage -> {
                nextPage()
            }
        }
    }


    private fun nextPage() {
        _episodesListState.value =
            _episodesListState.value.copy(page = _episodesListState.value.page + 1)
        loadEpisodes()
    }


    private fun loadEpisodes() {
        useCase.fetchEpisodes2(_episodesListState.value.page)
            .collectCommon(viewModelScope) { dataState ->
                val currentList = appendToEpisodeList(dataState.data ?: emptyList())
                _episodesListState.value =
                    _episodesListState.value.copy(
                        isIdle = false,
                        error = dataState.error,
                        episodeList = currentList.toList(),
                        isLoading = dataState.isLoading
                    )

            }
    }

    fun shouldLoadMoreItems(index: Int): Boolean {
        return useCase.indexGreaterThanPage(
            index,
            _episodesListState.value.page
        ) && _episodesListState.value.isLoading.not()
    }

    private fun appendToEpisodeList(fetchedList: List<Episode>): MutableList<Episode> {
        val currentList = mutableListOf<Episode>()
        currentList.addAll(_episodesListState.value.episodeList)
        currentList.addAll(fetchedList)
        return currentList
    }



}