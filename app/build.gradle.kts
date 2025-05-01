plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp) // Referência ao plugin KSP do Version Catalog
}

android {
    namespace = "com.example.phonedatabase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.phonedatabase"
        minSdk = 26
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation ("com.google.android.gms:play-services-wearable:19.0.0") // Ou a versão mais recente
    implementation("androidx.appcompat:appcompat:1.3.0") // ou uma versão mais recente
    implementation("androidx.core:core-ktx:1.6.0")


    //Para usar o Room no app
    implementation(libs.androidx.room.runtime)
    implementation(libs.play.services.wearable)  // Referência ao Room runtime
    ksp(libs.androidx.room.compiler)           // Referência ao Room compiler com KSP
    implementation(libs.androidx.room.ktx)    // Extensão Kotlin do Room

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
