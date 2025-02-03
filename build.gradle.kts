// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    kotlin("jvm") version "1.9.21" apply false
    id("com.google.devtools.ksp") version "2.1.10-1.0.29" apply false
}

buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "2.1.10"))
    }
}