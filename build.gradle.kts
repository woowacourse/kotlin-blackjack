plugins {
    java
    kotlin("jvm") version "1.5.10"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
}

group = "camp.nextstep.edu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.7.2")
    testImplementation("org.assertj", "assertj-core", "3.19.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    test {
        useJUnitPlatform()
    }
    ktlint {
        verbose.set(true)
        disabledRules.addAll("import-ordering")
    }
}
