plugins {
    kotlin("jvm") version "2.1.10"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.realparkourhelper"
version = "0.1"

repositories {
    mavenCentral()

    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven { url = uri("https://jitpack.io") }

    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    testImplementation(kotlin("test"))

    compileOnly("org.github.paperspigot:paperspigot-api:1.8.8-R0.1-20160806.221350-1")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.2.1")
    implementation(kotlin("stdlib"))

    implementation("org.slf4j:slf4j-simple:2.0.13")

    implementation("com.esotericsoftware:kryo:5.3.0")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
    }

    build {
        dependsOn(shadowJar)
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}