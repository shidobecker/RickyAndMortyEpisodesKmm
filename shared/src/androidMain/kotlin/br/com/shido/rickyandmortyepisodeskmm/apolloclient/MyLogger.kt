package br.com.shido.rickyandmortyepisodeskmm.apolloclient

actual class MyLogger {
    actual fun logMessage(message: String) {
        println(message)
    }
}