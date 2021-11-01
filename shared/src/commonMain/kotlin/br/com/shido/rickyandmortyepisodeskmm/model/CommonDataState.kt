package br.com.shido.rickyandmortyepisodeskmm.model

import br.com.shido.rickyandmortyepisodeskmm.exception.ApplicationException

data class CommonDataState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: ApplicationException? = null
) {

    companion object {
        fun <T> error(error: ApplicationException?): CommonDataState<T> {
            return CommonDataState(
                error = error,
                data = null,
            )
        }

        fun <T> data(data: T? = null): CommonDataState<T> {
            return CommonDataState(data = data,)
        }

        fun <T> loading() = CommonDataState<T>(isLoading = true)
    }
}
