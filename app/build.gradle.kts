plugins {
    id(BuildPlugins.androidApplication)
    kotlin(BuildPlugins.kotlinAndroid)
    kotlin(BuildPlugins.kotlinAndroidExtension)
    kotlin(BuildPlugins.kotlinKapt)
    id(BuildPlugins.googleServicesPlugin)
}

kapt{
    useBuildCache = false

    arguments {
        arg("dagger.gradle.incremental", true)
    }
}

android {
    compileSdkVersion(AndroidSdk.compileSdk)

    dataBinding {
        isEnabled = true
    }


    defaultConfig {
        applicationId = "com.example.playground"
        minSdkVersion(AndroidSdk.minSdk)
        targetSdkVersion(AndroidSdk.targetSdk)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompatLib)
    implementation(Libraries.legacySupportLib)
    testImplementation(TestLibraries.jUnitLib)
    androidTestImplementation(TestLibraries.testRunnerLib)
    androidTestImplementation(TestLibraries.espressoLib)

    //UI implementation
    implementation(Libraries.constraintLayoutLib)
    implementation(Libraries.cardViewLib)
    implementation(Libraries.recyclerViewLib)

    // Retrofit
    implementation(Libraries.retrofitLib)
    implementation(Libraries.retrofitRxAdapter)
    implementation(Libraries.retrofitMoshiConvertor)

    // LiveData & ViewModel
    implementation(Libraries.lifeCycleExtension)
    implementation(Libraries.lifeCycleViewModel)

    //RxJava
    implementation(Libraries.rxJavaLib)
    implementation(Libraries.rxAndroidLib)

    //Navigation UI
    implementation(Libraries.navigationFragmentktx)
    implementation(Libraries.navigationUi)
    implementation(Libraries.navigationFragment)

    //Room Database
    implementation(Libraries.roomLib)
    implementation(Libraries.roomRx)
    kapt(Libraries.roomCompiler)

    /* Dagger2 - We are going to use dagger.android which includes
     * support for Activity and fragment injection so we need to include
     * the following dependencies */
    implementation(Libraries.daggerLib)
    implementation(Libraries.daggerSupportLib)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerProcessor)

    //Paging Library
    implementation(Libraries.pagingLib)

    //Firebase
    implementation(Libraries.firebaseCore)

    //Firebase Database
    implementation(Libraries.firebaseDatabase)
    implementation(Libraries.firebaseFirestore)

    //Firebase Authentication
    implementation(Libraries.firebaseAuth)
    implementation(Libraries.googleServicesLib)

    //Glide
    implementation(Libraries.glideLib)
    kapt(Libraries.glideCompiler)
}

