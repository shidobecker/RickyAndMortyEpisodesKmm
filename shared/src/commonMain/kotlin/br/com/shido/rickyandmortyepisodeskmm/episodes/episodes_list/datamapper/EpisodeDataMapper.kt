package br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_list.datamapper

import br.com.shido.rickyandmortyepisodeskmm.episodes.common.datamapper.ResponseToDomain
import br.com.shido.rickyandmortyepisodeskmm.episodes.episodes_detail.datamapper.CharacterDataMapper
import br.com.shido.rickyandmortyepisodeskmm.fragment.EpisodeResultFields
import br.com.shido.rickyandmortyepisodeskmm.episodes.common.model.Episode

class EpisodeDataMapper (
    private val charactersDataMapper: CharacterDataMapper
        ): ResponseToDomain<EpisodeResultFields, Episode> {

    override fun toDomain(responseObject: EpisodeResultFields): Episode {
        val seasonEpisode = responseObject.episode?.substringAfter("E")
        val imageName = "image_${seasonEpisode}"

        val characterFields = responseObject.characters.mapNotNull {
            it?.fragments?.characterFields
        }

        return Episode(
            id = responseObject.id ?: "",
            name = responseObject.name ?: "",
            imageName = imageName,
            airDate = responseObject.air_date ?: "",
            episode = responseObject.episode ?: "",
            created = responseObject.created ?: "",
            characters = charactersDataMapper.toDomainList(characterFields)
        )
    }
}