object Jetpack {

    private const val lifecycleVersion = "2.4.0-alpha02"
    private const val lifecycleExtensionsVersion = "2.1.0"
    private const val lifecycleCoreVersion = "2.1.0"
    private const val navigationVersion = "2.3.1"
    private const val workManagerVersion = "2.2.0"
    private const val securityVersion = "1.1.0-alpha03"


    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleExtensionsVersion"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"

    const val pagingRuntime = "androidx.paging:paging-runtime-ktx:$lifecycleVersion"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    const val workManagerRuntime = "androidx.work:work-runtime:$workManagerVersion"
    const val workManagerKtx = "androidx.work:work-runtime-ktx:$workManagerVersion"

    const val security = "androidx.security:security-crypto:$securityVersion"


    const val archTestCore = "androidx.arch.core:core-testing:$lifecycleCoreVersion"
    const val workManagerTesting = "androidx.work:work-testing:$workManagerVersion"
}