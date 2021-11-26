package br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_detail.events

sealed class EpisodeDetailEvent {

    object LoadEpisode : EpisodeDetailEvent()
}