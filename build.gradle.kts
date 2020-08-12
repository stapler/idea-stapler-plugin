plugins {
    id("org.jetbrains.intellij") version "0.4.16"
    java
}

group = "org.kohsuke.stapler.idea"
version = "1.8"
val ideaVersion: String by project

repositories {
    mavenCentral()
    maven("https://repo.jenkins-ci.org/releases/")
}

dependencies {
    compile("org.jenkins-ci", "commons-jexl", "1.1-jenkins-20111212")
    compile("net.java.dev.textile-j", "textile-j", "2.2.864")

    //testCompile("junit", "junit", "4.12") // Not yet used
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = ideaVersion
    setPlugins("properties")
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    sinceBuild("110.00")
    untilBuild("")
    changeNotes("""
      Support IDEA 12.<br>
      Fix NullPointerException.<br>
      Resolved jelly namespace URIs to embedded XSDs to support syntax completion.""")
}