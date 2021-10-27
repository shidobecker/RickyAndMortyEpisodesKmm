package br.com.shido.rickyandmortyepisodeskmm.episodes_list.repository

import br.com.shido.rickyandmortyepisodeskmm.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    fun fetchEpisodes(page: Int): Flow<List<Episode>>

}