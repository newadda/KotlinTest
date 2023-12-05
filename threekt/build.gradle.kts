plugins {
    id("java")
    id ("org.jetbrains.kotlin.jvm") version "1.7.21"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.laht.threekt:core:r1-ALPHA-27")
}

tasks.test {
    useJUnitPlatform()
}