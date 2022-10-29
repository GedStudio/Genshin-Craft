import org.gradle.configurationcache.extensions.capitalized

val mojangMapped = false

plugins {
    id("java")
    kotlin("jvm") version "1.7.10"
    id("xyz.xenondevs.specialsource-gradle-plugin") version "1.0.0"
    id("xyz.xenondevs.string-remapper-gradle-plugin") version "1.0.0"
    id("xyz.xenondevs.nova.nova-gradle-plugin") version "0.11-SNAPSHOT"
}

group = "net.deechael"
version = "1.00.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.xenondevs.xyz/releases")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation(deps.nova)
    implementation(variantOf(deps.spigot) { classifier("remapped-mojang") })
}

addon {
    id.set("genshincraft")
    name.set("Genshin Craft")
    version.set(project.version.toString())
    novaVersion.set(deps.versions.nova)
    main.set("net.deechael.genshin.item.nova.GsItemNovaLoader")

    authors.add("DeeChael")
    // spigotResourceId.set(12345) TODO
}

spigotRemap {
    spigotVersion.set(deps.versions.spigot.get().substringBefore('-'))
    sourceJarTask.set(tasks.jar)
    spigotJarClassifier.set("")
}

remapStrings {
    remapGoal.set(if (mojangMapped) "mojang" else "spigot")
    spigotVersion.set(deps.versions.spigot.get())
    classes.set(listOf(

    ))
}

tasks {
    register<Copy>("addonJar") {
        group = "build"
        dependsOn("addon", if (mojangMapped) "jar" else "remapObfToSpigot")

        from(File(File(project.buildDir, "libs"), "${project.name}-${project.version}.jar"))
        into(System.getProperty("outDir")?.let(::File) ?: project.buildDir)
    }
}