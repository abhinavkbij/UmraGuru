buildscript {
    val agpVersion = "8.1.4"
    val kotlinVersion = "1.9.23"
    val hiltVersion = "2.47"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$agpVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

plugins {
    id("com.android.application") version "8.1.4" apply false
    id("com.android.library") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
tasks.withType<JavaCompile>().configureEach {
    if (JavaVersion.current() != JavaVersion.VERSION_17) {
        throw GradleException("This project requires Java 17. Current version is ${JavaVersion.current()}. Check your JAVA_HOME environment variable.")
    }
}