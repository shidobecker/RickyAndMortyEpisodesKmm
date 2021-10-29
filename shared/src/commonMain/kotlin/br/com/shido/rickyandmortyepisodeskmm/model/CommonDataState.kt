package br.com.shido.rickyandmortyepisodeskmm.model

data class CommonDataState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {

    companion object {
        fun <T> error(message: String?): CommonDataState<T> {
            return CommonDataState(
                errorMessage = message,
                data = null,
            )
        }

        fun <T> data(message: String? = null, data: T? = null, ): CommonDataState<T> {
            return CommonDataState(
                errorMessage = message,
                data = data,
            )
        }

        fun <T> loading() = CommonDataState<T>(isLoading = true)
    }

}