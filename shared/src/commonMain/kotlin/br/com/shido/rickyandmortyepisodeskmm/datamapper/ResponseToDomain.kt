package br.com.shido.rickyandmortyepisodeskmm.datamapper

interface ResponseToDomain<Response, Domain> {

    fun toDomain(responseObject: Response): Domain

    fun toDomainList(responseList: List<Response>): List<Domain> {
        return responseList.map { toDomain(it) }
    }

}