package br.com.shido.rickyandmortyepisodeskmm.android.episode_detail.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.model.EpisodeState
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.usecase.EpisodeDetailUseCase
import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_detail.events.EpisodeDetailEvent

class EpisodeDetailViewModel(private val useCase: EpisodeDetailUseCase) : ViewModel() {

    lateinit var episodeId: String
    lateinit var episodeImage: String

    private val _episodeState: MutableState<EpisodeState> = mutableStateOf(EpisodeState())
    val episodeState: State<EpisodeState> get() = _episodeState

    fun setArguments(episodeId: String, episodeImage: String) {
        this.episodeId = episodeId
        this.episodeImage = episodeImage
    }

    fun onTriggerEvent(event: EpisodeDetailEvent) {
        when (event) {
            EpisodeDetailEvent.LoadEpisode -> fetchEpisode()
        }
    }


    private fun fetchEpisode() {
        useCase.fetchEpisode(episodeId).collectCommon(viewModelScope) { dataState ->
            _episodeState.value =
                _episodeState.value.copy(
                    isIdle = false,
                    error = dataState.error,
                    episode = dataState.data,
                    isLoading = dataState.isLoading
                )
        }
    }

}