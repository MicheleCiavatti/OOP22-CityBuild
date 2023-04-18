buildscript {
    

    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven { 
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/") 
            }
        google()
    }
    dependencies {
        

    }
}

allprojects {
    apply (plugin = "eclipse")

    version = "1.0"
    extra.apply {
        set("appName", "CityBuild")
        set("gdxVersion", "1.11.0")
        set("roboVMVersion", "2.3.16")
        set("box2DLightsVersion", "1.5")
        set("ashleyVersion", "1.7.4")
        set("aiVersion", "1.8.2")
        set("gdxControllersVersion", "2.2.1")
    }

    repositories {
        mavenLocal()
        mavenCentral()
        google()
        gradlePluginPortal()
        maven { 
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/") 
            }
        maven { 
            url = uri("https://oss.sonatype.org/content/repositories/releases/") 
            }
        maven { 
            url = uri("https://jitpack.io") 
            }
    }
}

project(":desktop") {
    apply (plugin = "java-library")


    dependencies {
        implementation project(":core")
        api ("com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion")
        api ("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop")
        api ("com.badlogicgames.gdx:gdx-box2d-platform:$gdxVersion:natives-desktop")
        api ("com.badlogicgames.gdx:gdx-lwjgl3-glfw-awt-macos:$gdxVersion")
    }
}

project(":core") {
    apply (plugin = "java-library")


    dependencies {
        api ("com.badlogicgames.gdx:gdx:$gdxVersion")
        api ("com.badlogicgames.box2dlights:box2dlights:$box2DLightsVersion")
        api ("com.badlogicgames.gdx:gdx-box2d:$gdxVersion")
        implementation("org.yaml:snakeyaml:1.33")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }
}
