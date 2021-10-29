package br.com.shido.rickyandmortyepisodeskmm.model




data class EpisodeListState(
    val isIdle: Boolean = true,
    val isLoading: Boolean = false,
    val errorHappened: Boolean = false,
    val page: Int = 1,
    val episodeList: List<Episode> = emptyList(),
) {

    constructor() : this(
        isIdle = true,
        isLoading = false,
        page = 1,
        errorHappened = false,
        episodeList = emptyList()
    )
}