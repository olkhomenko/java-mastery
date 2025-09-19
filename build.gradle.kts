import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import net.ltgt.gradle.errorprone.errorprone
import org.gradle.api.tasks.testing.Test
import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    jacoco
    id("com.diffplug.spotless") version "6.25.0"
    id("net.ltgt.errorprone") version "3.1.0" apply false
    id("me.champeau.jmh") version "0.7.2" apply false
    id("info.solidsoft.pitest") version "1.15.0" apply false
}

val javaVersionProp = (findProperty("javaVersion") as String?) ?: "17"

subprojects {
    apply(plugin = "java")
    apply(plugin = "jacoco")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "net.ltgt.errorprone")

    repositories { mavenCentral() }

    extensions.configure<JavaPluginExtension> {
        toolchain { languageVersion.set(JavaLanguageVersion.of(javaVersionProp.toInt())) }
    }

    dependencies {
        "testImplementation"(platform("org.junit:junit-bom:5.11.0"))
        "testImplementation"("org.junit.jupiter:junit-jupiter")
        "testImplementation"("org.assertj:assertj-core:3.26.3")
        "testImplementation"("org.mockito:mockito-core:5.13.0")
        "testImplementation"("net.jqwik:jqwik:1.8.2")
        "implementation"("com.google.guava:guava:33.4.8-jre")

        // ✅ this resolves "Unresolved reference: errorprone"
        add("errorprone", "com.google.errorprone:error_prone_core:2.41.0")
    }

    tasks.withType<JavaCompile>().configureEach {
        // Remove any default should-stop flags Gradle may have added
        options.compilerArgs.removeIf { it.startsWith("--should-stop=") }
        // Error Prone needs FLOW instead of INIT
        options.compilerArgs.add("--should-stop=ifError=FLOW")

        // your existing bits
        options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
        options.errorprone.isEnabled.set(true)
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        reports.html.required.set(true)
    }

// ✅ Configure jacoco report task safely (typed + depends on 'test')
    tasks.named<JacocoReport>("jacocoTestReport") {
        dependsOn(tasks.named("test"))
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }

    spotless {
        java {
            googleJavaFormat()
            target("src/**/*.java")
        }
    }
}
