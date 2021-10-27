package br.com.shido.rickyandmortyepisodeskmm.android.episode_list.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.shido.rickyandmortyepisodeskmm.android.extensions.setError
import br.com.shido.rickyandmortyepisodeskmm.android.extensions.setLoading
import br.com.shido.rickyandmortyepisodeskmm.android.extensions.setSuccess
import br.com.shido.rickyandmortyepisodeskmm.android.extensions.w
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.usecase.EpisodeListUseCase
import br.com.shido.rickyandmortyepisodeskmm.model.DataState
import br.com.shido.rickyandmortyepisodeskmm.model.Episode
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EpisodeListViewModel(private val useCase: EpisodeListUseCase) : ViewModel() {

    private val _episodesList: MutableState<DataState<List<Episode>>> =
        mutableStateOf(DataState.Idle)
    val episodesList get() = _episodesList

    fun fetchEpisodes() {
        viewModelScope.launch {
            _episodesList.setLoading()

            delay(2000)

            useCase.fetchEpisodes().catch {
                _episodesList.setError(it)
            }.collect {
                _episodesList.setSuccess(it)

                it.forEach {
                    w("Data $it")
                }
            }
        }
    }

}