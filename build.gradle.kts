import groovy.json.JsonSlurper
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.2.3"
}

group = "com.fastasyncworldedit.playground"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    implementation(platform("com.intellectualsites.bom:bom-newest:1.43")) // Ref: https://github.com/IntellectualSites/bom
    compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Core")
    compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Bukkit") { isTransitive = false }

    annotationProcessor("com.fastasyncworldedit:FastAsyncWorldEdit-Libs-Ap:2.9.1")
    annotationProcessor("com.google.guava:guava:21.0")
    compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Libs-Ap:2.9.1")

    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
tasks {
    register("cacheLatestFaweArtifact") {
        val lastSuccessfulBuildUrl = uri("https://ci.athion.net/job/FastAsyncWorldEdit/lastSuccessfulBuild/api/json").toURL()
        val artifact = ((JsonSlurper().parse(lastSuccessfulBuildUrl) as Map<*, *>)["artifacts"] as List<*>)
            .map { it as Map<*, *> }
            .map { it["fileName"] as String }
            .first { it -> it.contains("Bukkit") }
        project.ext["faweArtifact"] = artifact
    }
    runServer {
        dependsOn(getByName("cacheLatestFaweArtifact"))
        minecraftVersion("1.20.4")
        jvmArgs("-DPaper.IgnoreJavaVersion=true", "-Dcom.mojang.eula.agree=true")
        downloadPlugins {
            url("https://ci.athion.net/job/FastAsyncWorldEdit/lastSuccessfulBuild/artifact/artifacts/${project.ext["faweArtifact"]}")
        }
    }
}
bukkit {
    description = "This is a simple playground plugin for fawe functions or testing propose of some plugin ideas"
    website = "https://www.spigotmc.org/resources/fastasyncworldedit.13932/"
    author = "IntellectualSites Team and Contributors"
    main = "com.fastasyncworldedit.playground.Playground"
    apiVersion = "1.18"

    permissions {
        register("playground.spikebrush") {
            default = BukkitPluginDescription.Permission.Default.TRUE
        }
    }
}