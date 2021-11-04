import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(KotlinPlugins.multiplatform)
    id(Plugins.androidLibrary)

    id(Apollo.apolloPluginId).version(Apollo.apolloVersion)
    kotlin("kapt")

}


kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Kotlinx.datetime)

                //Coroutines versions can be found here: https://kotlinlang.org/docs/kmm-concurrency-and-coroutines.html#multithreaded-coroutines
                    implementation(Kotlin.kotlinCoroutinesNatives) {
                        version {
                            strictly(Kotlin.coroutinesNativeVersion)
                        }
                    }

                // Koin for Kotlin Multiplatform
                implementation(Koin.koinCore)

                //Apollo for Kotlin Multiplatform
                implementation(Apollo.apolloRuntimeKotlin)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

            }
        }
        val androidMain by getting{
            dependencies{
                implementation ("androidx.room:room-runtime:2.3.0")

            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

dependencies {
    "kapt"("androidx.room:room-compiler:2.3.0")
}

android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 30
    }
}

//For connecting to iOS:
//https://blog.jetbrains.com/kotlin/2021/07/multiplatform-gradle-plugin-improved-for-connecting-kmm-modules/