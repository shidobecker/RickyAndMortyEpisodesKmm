object Koin {
    const val koinVersion = "3.1.0"

    const val koinCore = "io.insert-koin:koin-core:$koinVersion"

    const val koinTest = "io.insert-koin:koin-test:$koinVersion"

    // Koin main features for Android (Scope,ViewModel ...)
    const val koinAndroid = "io.insert-koin:koin-android:$koinVersion"

    // Koin for Jetpack WorkManager
    const val koinWorkManager =
        "io.insert-koin:koin-androidx-workmanager:$koinVersion"

    // Koin for Jetpack Compose (unstable version)
    const val koinCompose = "io.insert-koin:koin-androidx-compose:$koinVersion"

    // Koin for JUnit 4
    const val koinJUnit4 = "io.insert-koin:koin-test-junit4:$koinVersion"

    // Koin for JUnit 5
    const val koinJUnit5 = "io.insert-koin:koin-test-junit5:$koinVersion"
}