package br.com.shido.rickyandmortyepisodeskmm.episodes_list.model

import br.com.shido.rickyandmortyepisodeskmm.exception.ApplicationException


data class EpisodeState(
    val isIdle: Boolean = true,
    val isLoading: Boolean = false,
    val error: ApplicationException? = null,
    val page: Int = 1,
    val episode: Episode? = null,
) {

    constructor() : this(
        isIdle = true,
        isLoading = false,
        page = 1,
        error = null,
        episode = null
    )
}