package br.com.shido.rickyandmortyepisodeskmm.android.extensions

import timber.log.Timber

private const val noMessageProvided = "no message provided"

fun Any.e(any: Any? = noMessageProvided) {
    Timber.e(any.toString())
}

fun Any.w(any: Any? = noMessageProvided) {
    Timber.w(any.toString())
}

fun Any.d(any: Any? = noMessageProvided) {
    Timber.d(any.toString())
}

fun Any.i(any: Any? = noMessageProvided) {
    Timber.i(any.toString())
}