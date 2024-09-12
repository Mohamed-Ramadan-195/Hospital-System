plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("androidx.room")
}

android {
    namespace = "com.example.hospitalsystem"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hospitalsystem"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.play.services.cast.framework)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // layout
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.constraintlayout)

    // material design
    implementation(libs.material)

    // size
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)

    // recycler view
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.recyclerview.selection)

    // nav fragment
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // Http Logging Interceptor
    implementation(libs.logging.interceptor)

    // kotlin coroutine
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    // view model
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // live data
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Room Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.room.compiler)
    // noinspection KaptUsageInsteadOfKsp
    kapt(libs.room.compiler)

    // Lottie
    implementation(libs.lottie)

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // biometric
    implementation(libs.androidx.biometric.ktx)
}