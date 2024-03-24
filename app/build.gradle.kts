plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("com.google.devtools.ksp")
}
android {
    namespace = "com.ihiviko.dogsapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ihiviko.dogsapi"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.github.bumptech.glide:glide:4.13.0")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation ("junit:junit:4.13.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation ("androidx.fragment:fragment-ktx:1.6.1")
    ksp("com.github.bumptech.glide:compiler:4.15.1")

    implementation ("androidx.room:room-ktx:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")



    // dependencias de mockWebserver

    testImplementation ("org.mockito:mockito-core:3.6.0")
    testImplementation ("org.mockito:mockito-inline:3.6.0")


    // Dependencia de OkHttp
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")

    // Dependencia de OkHttp MockWebServer (para pruebas)
    testImplementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.2")
    testImplementation ("io.mockk:mockk:1.12.0")



    implementation("com.google.dagger:hilt-android:2.44")
    kspTest("com.google.dagger:hilt-android-compiler:2.44")

    // Para pruebas con Robolectric
    testImplementation("com.google.dagger:hilt-android-testing:2.44")
    kspAndroidTest("com.google.dagger:hilt-android-compiler:2.44")

    // Para pruebas instrumentadas
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kspAndroidTest("com.google.dagger:hilt-android-compiler:2.44")
}