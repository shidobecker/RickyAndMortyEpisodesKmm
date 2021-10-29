package br.com.shido.rickyandmortyepisodeskmm.episodes_list.events

sealed class EpisodeListEvents {

    object LoadEpisodes: EpisodeListEvents()

    object NextPage: EpisodeListEvents()

}