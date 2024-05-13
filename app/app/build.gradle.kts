plugins {
    alias(libs.plugins.androidApplication)
    id("com.zeroc.gradle.ice-builder.slice") version("1.5.0")
}

android {
    namespace = "fr.frcsbcn.soup"
    compileSdk = 34

    defaultConfig {
        applicationId = "fr.frcsbcn.soup"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
    dataBinding{
         enable = true
    }
}

dependencies {
    implementation(libs.libvlc.all)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.ice)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}