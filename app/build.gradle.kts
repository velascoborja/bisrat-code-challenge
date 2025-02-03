plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.touch_surgery.digital_surgery"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.touch_surgery.digital_surgery"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"

        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
        }
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation (libs.androidx.material.icons.extended)

    //retrofit & moshi serializers
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)
    implementation(libs.converter.moshi)
    implementation(libs.converter.gson)

    //okhttp3
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)

    // Navigation
    implementation (libs.androidx.navigation.compose)

    //Room database
    implementation(libs.androidx.room.runtime)
    implementation(libs.core.ktx)
    testImplementation(libs.junit.jupiter)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)


    //KOIN dependency injection
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)


    //image libraries
    implementation(libs.picasso)
    implementation (libs.coil)
    implementation (libs.coil.compose)

    //Splash Api
    implementation(libs.androidx.core.splashscreen)


    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)


    //testing tools
    testImplementation(libs.junit)
    implementation(libs.mockito.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation (libs.mockk.android)

    testImplementation(libs.insert.koin.koin.test)
    androidTestImplementation(libs.insert.koin.koin.test)
    testImplementation(libs.koin.test.junit4)
    testImplementation(libs.testing)
    androidTestImplementation(libs.mockwebserver)
    testImplementation (libs.mockwebserver)
    testImplementation(libs.mockk)
    testImplementation (libs.kotlinx.coroutines.test)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation (libs.mockito.kotlin)
    androidTestImplementation(libs.androidx.runner)

    androidTestUtil(libs.androidx.orchestrator)
}