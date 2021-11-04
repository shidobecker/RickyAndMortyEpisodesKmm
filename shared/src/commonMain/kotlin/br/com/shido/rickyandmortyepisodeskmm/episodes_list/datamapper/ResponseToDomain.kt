package br.com.shido.rickyandmortyepisodeskmm.episodes_list.datamapper

interface ResponseToDomain<Response, Domain> {

    fun toDomain(responseObject: Response): Domain

    fun toDomainList(responseList: List<Response>): List<Domain> {
        return responseList.map { toDomain(it) }
    }

}