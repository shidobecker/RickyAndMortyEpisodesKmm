package br.com.shido.rickyandmortyepisodeskmm.episodes_list.datamapper

import br.com.shido.rickyandmortyepisodeskmm.fragment.EpisodeResultFields
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.model.Episode

class EpisodeDataMapper : ResponseToDomain<EpisodeResultFields, Episode> {

    override fun toDomain(responseObject: EpisodeResultFields): Episode {
        val seasonEpisode = responseObject.episode?.substringAfter("E")
        val imageName = "image_${seasonEpisode}"
        return Episode(
            id = responseObject.id ?: "",
            name = responseObject.name ?: "",
            imageName = imageName,
            airDate = responseObject.air_date ?: "",
            episode = responseObject.episode ?: "",
            created = responseObject.created ?: ""
        )
    }
}