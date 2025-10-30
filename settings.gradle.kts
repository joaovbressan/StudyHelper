// SUBSTITUA O CONTEÚDO DE settings.gradle.kts POR ISTO:

pluginManagement {
    repositories {
        google()
        mavenCentral()
        // ESSA LINHA É CRUCIAL para encontrar plugins como KSP e Hilt
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "StudyHelper"
include(":app")