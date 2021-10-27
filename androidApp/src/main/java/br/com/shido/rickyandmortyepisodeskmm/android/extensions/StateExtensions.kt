package br.com.shido.rickyandmortyepisodeskmm.android.extensions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import br.com.shido.rickyandmortyepisodeskmm.model.DataState


fun <T> MutableState<DataState<T>>.setError(throwable: Throwable) {
    value = DataState.Error(throwable)
}

fun <T> MutableState<DataState<T>>.setLoading() {
    value = DataState.Loading
}

fun <T> MutableState<DataState<T>>.setIdle() {
    value = DataState.Idle
}

fun <T> MutableState<DataState<T>>.setSuccess(data: T) {
    value = DataState.Success(data)
}


inline fun <reified T> State<DataState<T>>.getValueIfSuccess(): T? {
    return if (this.value is DataState.Success<T>) {
        (this.value as DataState.Success<T>).data
    } else {
        null
    }
}

inline fun <reified T> State<DataState<T>>.getError(): Throwable? {
    return if (this.value is DataState.Error) {
        (this.value as DataState.Error).exception
    } else {
        null
    }
}

fun <T> State<DataState<T>>.isSuccess(): Boolean {
    return this.value is DataState.Success
}

fun <T> State<DataState<T>>.isLoading(): Boolean {
    return this.value is DataState.Loading
}

fun <T> State<DataState<T>>.isError(): Boolean {
    return this.value is DataState.Error
}

fun <T> State<DataState<T>>.isIdle(): Boolean {
    return this.value is DataState.Idle
}

fun <T> State<DataState<T>>.onError(code: (Throwable) -> Unit) {
    if (this.value is DataState.Error) {
        val exception = (this.value as DataState.Error).exception
        code(exception)
    }
}

fun <T> State<DataState<T>>.onLoading(code: () -> Unit) {
    if (this.value is DataState.Loading) {
        code()
    }
}

fun <T> State<DataState<T>>.onSuccess(code: (T) -> Unit) {
    if (this.value is DataState.Success) {
        val data = (this.value as DataState.Success).data
        code(data)
    }
}