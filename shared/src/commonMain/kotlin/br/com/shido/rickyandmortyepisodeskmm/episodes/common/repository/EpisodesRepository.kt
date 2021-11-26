package br.com.shido.rickyandmortyepisodeskmm.episodes.common.repository

import br.com.shido.rickyandmortyepisodeskmm.episodes.common.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    fun fetchEpisodes(page: Int): Flow<List<Episode>>

    fun fetchEpisode(id: String): Flow<Episode>

}