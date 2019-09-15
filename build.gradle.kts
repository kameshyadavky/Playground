// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(ClassPaths.androidGradlePlugin)
        classpath(ClassPaths.kotlinGradlePlugin)
        classpath(ClassPaths.googleServicesPluginLib)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}


tasks.register("clean").configure {
    delete("build")
}
