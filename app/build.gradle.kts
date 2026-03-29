plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.training.ecommercetrainingproject"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.training.ecommercetrainingproject"
        minSdk = 23
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
    kotlin{
        compilerOptions{
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.activity:activity:1.13.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    // google official Splash Screen API dependency
    implementation("androidx.core:core-splashscreen:1.0.1")

    // ViewModel scope and lifecycle extensions
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

    // To use 'by viewModels()' in Activities
    implementation("androidx.activity:activity-ktx:1.10.0")
    // To use 'by viewModels()' or 'by activityViewModels()' in Fragments
    implementation("androidx.fragment:fragment-ktx:1.8.5")

    // Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:34.11.0"))
    // Analytics
    implementation("com.google.firebase:firebase-analytics")
    // Crashlytics SDK
    implementation("com.google.firebase:firebase-crashlytics")

    // Reactive Network library to listen network connectivity
    implementation("com.github.pwittchen:reactivenetwork-rx2:3.0.8")

}