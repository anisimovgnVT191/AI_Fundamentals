import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    application
}

group = "me.tewat"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("org.jetbrains.lets-plot:lets-plot-common:2.4.0")
    implementation ("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.0.0")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.4.0")

}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}