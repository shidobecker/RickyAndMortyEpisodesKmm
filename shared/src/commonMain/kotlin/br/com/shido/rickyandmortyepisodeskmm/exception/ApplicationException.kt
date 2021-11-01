package br.com.shido.rickyandmortyepisodeskmm.exception

const val ApplicationExceptionCode = "406"

class ApplicationException(
    val code: String = ApplicationExceptionCode,
    override val message: String?
) : Exception(message) {

    fun isDefaultApplicationException() = this.code == ApplicationExceptionCode

}