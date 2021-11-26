package br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_list.events

sealed class EpisodeListEvent {

    object LoadEpisodes: EpisodeListEvent()

    object NextPage: EpisodeListEvent()

}