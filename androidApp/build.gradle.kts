plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation(AndroidX.appCompat)

    implementation(Compose.runtime)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiTooling)
    implementation(Compose.foundation)
    implementation(Compose.compiler)
    implementation(Compose.constraintLayout)
    implementation(Compose.materialIcons)
    implementation(Compose.materialIconsExtended)
    implementation(Compose.activity)
    implementation(Compose.navigation)

    implementation(Google.material)

    implementation(Kotlinx.datetime)
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "br.com.shido.rickyandmortyepisodeskmm.android"
        minSdk = 26
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}