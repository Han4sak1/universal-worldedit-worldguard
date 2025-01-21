import io.izzel.taboolib.gradle.Basic
import io.izzel.taboolib.gradle.Bukkit

val taboolib_version: String by project

plugins {
    id("io.izzel.taboolib") version "2.0.22"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.sk89q.worldedit:WorldEdit:7")
    compileOnly(fileTree("libs"))
    compileOnly("ink.ptms.core:v11903:11903:mapped")
    compileOnly("ink.ptms.core:v11903:11903:universal")
}

// 子模块
taboolib {
    env {
        install(Basic, Bukkit)
    }
    subproject = true
    version {
        taboolib = taboolib_version
    }
}