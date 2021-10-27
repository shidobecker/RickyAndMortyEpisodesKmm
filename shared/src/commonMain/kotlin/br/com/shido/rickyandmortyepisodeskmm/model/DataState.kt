package br.com.shido.rickyandmortyepisodeskmm.model


sealed class DataState<out T : Any?> {
    object Idle : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val exception: Throwable) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}