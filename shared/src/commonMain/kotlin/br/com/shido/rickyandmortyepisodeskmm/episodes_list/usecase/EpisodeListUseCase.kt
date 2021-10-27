package br.com.shido.rickyandmortyepisodeskmm.episodes_list.usecase

import br.com.shido.rickyandmortyepisodeskmm.episodes_list.repository.EpisodesRepository
import br.com.shido.rickyandmortyepisodeskmm.model.Episode
import kotlinx.coroutines.flow.Flow

class EpisodeListUseCase(private val repository: EpisodesRepository) {

    fun fetchEpisodes(): Flow<List<Episode>> = repository.fetchEpisodes(1)

}