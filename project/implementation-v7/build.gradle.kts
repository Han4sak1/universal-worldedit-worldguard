import io.izzel.taboolib.gradle.Basic
import io.izzel.taboolib.gradle.Bukkit

val taboolib_version: String by project

plugins {
    id("io.izzel.taboolib") version "2.0.22"
}

dependencies {
    compileOnly(project(":project:common"))
    compileOnly("com.sk89q.worldedit:WorldEdit:7")
    compileOnly(fileTree("libs"))
    compileOnly("ink.ptms.core:v12004:12004:mapped")
    compileOnly("ink.ptms.core:v12004:12004:universal")
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