import edu.sc.seis.launch4j.tasks.DefaultLaunch4jTask
import org.gradle.kotlin.dsl.test
import org.gradle.kotlin.dsl.testImplementation

plugins {
    kotlin("jvm") version "2.0.20"
    id("edu.sc.seis.launch4j") version "3.0.6"
}

group = "com.isycat"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(12)
}

tasks.jar {
    manifest.attributes["Main-Class"] = "com.isycat.MainKt"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.withType<DefaultLaunch4jTask> {
    outfile = "${rootProject.name}.exe"
    mainClassName = "com.isycat.MainKt"
    icon = "$projectDir/icons/icon.ico"
    productName = rootProject.name
}

defaultTasks("createAllExecutables")
