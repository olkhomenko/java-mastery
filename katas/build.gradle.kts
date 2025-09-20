plugins {
    id("me.champeau.jmh")
    id("info.solidsoft.pitest")
}

group = "io.oleh"
version = "0.1.0"

dependencies {
    "implementation"("com.google.guava:guava:33.4.8-jre")
}

pitest {
    junit5PluginVersion.set("1.2.1")
    targetClasses.set(listOf("io.oleh.katas.*"))
    targetTests.set(listOf("io.oleh.katas.*"))
    mutationThreshold.set(70)
    threads.set(2)
    timestampedReports.set(false)
}

jmh {
    warmupIterations.set(2)
    iterations.set(5)
    fork.set(1)
}
