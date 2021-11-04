package br.com.shido.rickyandmortyepisodeskmm.episodes_list.model

import br.com.shido.rickyandmortyepisodeskmm.exception.ApplicationException


data class EpisodeListState(
    val isIdle: Boolean = true,
    val isLoading: Boolean = false,
    val error: ApplicationException? = null,
    val page: Int = 1,
    val episodeList: List<Episode> = emptyList(),
) {

    constructor() : this(
        isIdle = true,
        isLoading = false,
        page = 1,
        error = null,
        episodeList = emptyList()
    )
}