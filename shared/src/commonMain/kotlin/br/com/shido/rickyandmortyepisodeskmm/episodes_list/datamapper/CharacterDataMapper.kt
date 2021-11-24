package br.com.shido.rickyandmortyepisodeskmm.episodes_list.datamapper

import br.com.shido.rickyandmortyepisodeskmm.episodes_list.model.Character
import br.com.shido.rickyandmortyepisodeskmm.fragment.CharacterFields

class CharacterDataMapper : ResponseToDomain<CharacterFields, Character> {

    override fun toDomain(responseObject: CharacterFields): Character {
        return Character(
            id = responseObject.id ?: "",
            name = responseObject.name ?: "",
            status = responseObject.status ?: "",
            species = responseObject.species ?: "",
            image = responseObject.image ?: ""
        )
    }
}