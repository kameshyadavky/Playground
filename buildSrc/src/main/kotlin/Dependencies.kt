const val kotlinVersion = "1.3.50"

object BuildPlugins{
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "android"
    const val kotlinAndroidExtension = "android.extensions"
    const val kotlinKapt = "kapt"
    const val googleServicesPlugin = "com.google.gms.google-services"
}

object ClassPaths{
    object Version{
        const val buildToolVersion = "3.5.0"
        const val googleServicesPlugin = "4.3.2"
    }

    const val googleServicesPluginLib = "com.google.gms:google-services:${Version.googleServicesPlugin}"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Version.buildToolVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}"
}

object AndroidSdk {
    const val minSdk = 21
    const val compileSdk = 28
    const val targetSdk = 29
}

object Libraries{

    object Version{
        const val appCompatVersion = "1.1.0"
        const val legacySupportVersion = "1.0.0"
        const val constraintLayoutVersion = "1.1.3"
        const val cardViewVersion = "1.0.0"
        const val recyclerViewVersion = "1.1.0-beta04"
        const val retrofitVersion = "2.6.1"
        const val lifeCycleVersion = "2.2.0-alpha04"
        const val rxVersion = "2.2.10"
        const val navigationVersion = "1.0.0"
        const val roomVersion = "2.2.0-rc01"
        const val daggerVersion = "2.24"
        const val pagingVersion = "1.0.1"
        const val firebaseCoreVersion = "17.2.0"
        const val firebaseDatabaseVersion = "19.1.0"
        const val firebaseFirestoreVersion = "21.1.0"
        const val firebaseAuthVersion = "19.0.0"
        const val googlePlayServicesVersion = "17.0.0"
        const val glideVersion ="4.9.0"
    }

    //Kotlin standard library
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${kotlinVersion}"

    //Legacy support
    const val legacySupportLib = "androidx.legacy:legacy-support-v4:${Version.legacySupportVersion}"

    //Lifecycle
    const val lifeCycleExtension = "androidx.lifecycle:lifecycle-extensions:${Version.lifeCycleVersion}"
    const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Version.lifeCycleVersion}"


    //Ui Library
    const val appCompatLib = "androidx.appcompat:appcompat:${Version.appCompatVersion}"
    const val constraintLayoutLib = "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"
    const val cardViewLib = "androidx.cardview:cardview:${Version.cardViewVersion}"
    const val recyclerViewLib = "androidx.recyclerview:recyclerview:${Version.recyclerViewVersion}"
    const val navigationFragmentktx = "android.arch.navigation:navigation-fragment-ktx:${Version.navigationVersion}"
    const val navigationUi = "android.arch.navigation:navigation-ui-ktx:${Version.navigationVersion}"
    const val navigationFragment = "android.arch.navigation:navigation-fragment:${Version.navigationVersion}"
    const val pagingLib = "android.arch.paging:runtime:${Version.pagingVersion}"


    //Retrofit
    const val retrofitLib = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofitVersion}"
    const val retrofitMoshiConvertor = "com.squareup.retrofit2:converter-moshi:${Version.retrofitVersion}"

    //Rx
    const val rxJavaLib = "io.reactivex.rxjava2:rxjava:${Version.rxVersion}"
    const val rxAndroidLib = "io.reactivex.rxjava2:rxandroid:2.1.0"


    //Room
    const val roomLib = "androidx.room:room-runtime:${Version.roomVersion}"
    const val roomRx = "androidx.room:room-rxjava2:${Version.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.roomVersion}"


    //Dagger 2
    const val daggerLib = "com.google.dagger:dagger-android:${Version.daggerVersion}"
    const val daggerSupportLib = "com.google.dagger:dagger-android-support:${Version.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.daggerVersion}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Version.daggerVersion}"


    //Firebase
    const val firebaseCore = "com.google.firebase:firebase-core:${Version.firebaseCoreVersion}"
    const val firebaseDatabase = "com.google.firebase:firebase-database:${Version.firebaseDatabaseVersion}"
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Version.firebaseAuthVersion}"
    const val firebaseFirestore = "com.google.firebase:firebase-firestore:${Version.firebaseFirestoreVersion}"
    const val googleServicesLib = "com.google.android.gms:play-services-auth:${Version.googlePlayServicesVersion}"

    //Glide
    const val glideLib = "com.github.bumptech.glide:glide:${Version.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glideVersion}"
}

object TestLibraries{
    object Version{
        const val jUnitVersion = "4.12"
        const val testRunnerVersion = "1.3.0-alpha02"
        const val espressoVersion = "3.3.0-alpha02"
    }

    const val jUnitLib = "junit:junit:${Version.jUnitVersion}"
    const val testRunnerLib = "androidx.test:runner:${Version.testRunnerVersion}"
    const val espressoLib = "androidx.test.espresso:espresso-core:${Version.espressoVersion}"
}