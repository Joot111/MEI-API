plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "pt.ipt.dama2026.api"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "pt.ipt.dama2026.api"
        minSdk = 28
        targetSdk = 36
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Add Retrofit Dependencies
    // https://square.github.io/retrofit/
    //implementation("com.squareup.retrofit2:retrofit:2.6.2")
    //implementation("com.squareup.retrofit2:converter-gson:2.3.0")
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}