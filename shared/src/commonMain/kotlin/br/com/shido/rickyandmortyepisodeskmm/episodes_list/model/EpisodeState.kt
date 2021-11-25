package br.com.shido.rickyandmortyepisodeskmm.episodes_list.model

import br.com.shido.rickyandmortyepisodeskmm.exception.ApplicationException


data class EpisodeState(
    val isIdle: Boolean = true,
    val isLoading: Boolean = false,
    val error: ApplicationException? = null,
    val episode: Episode? = null,
) {

    constructor() : this(
        isIdle = true,
        isLoading = false,
        error = null,
        episode = null
    )
}