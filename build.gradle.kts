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
    val appName by extra("CityBuild")
    val gdxVersion by extra("1.11.0")
    val roboVMVersion by extra("2.3.16")
    val box2DLightsVersion by extra("1.5")
    val ashleyVersion by extra("1.7.4")
    val aiVersion by extra("1.8.2")
    val gdxControllersVersion by extra("2.2.1")

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
        "implementation"(project(":core"))
        "api" ("com.badlogicgames.gdx:gdx-backend-lwjgl3:1.11.0")
        "api" ("com.badlogicgames.gdx:gdx-platform:1.11.0:natives-desktop")
        "api" ("com.badlogicgames.gdx:gdx-box2d-platform:1.11.0:natives-desktop")
        "api" ("com.badlogicgames.gdx:gdx-lwjgl3-glfw-awt-macos:1.11.0")
    }

    configurations {
        "implementation" {
            resolutionStrategy.failOnVersionConflict()
        }
        "api" {
            resolutionStrategy.failOnVersionConflict()
        }
        "testImplementation" {
            resolutionStrategy.failOnVersionConflict()
        }
        "testRuntimeOnly" {
            resolutionStrategy.failOnVersionConflict()
        }
    }
}

project(":core") {
    apply (plugin = "java-library")


    dependencies {
        "api" ("com.badlogicgames.gdx:gdx:1.11.0")
        "api" ("com.badlogicgames.box2dlights:box2dlights:1.5")
        "api" ("com.badlogicgames.gdx:gdx-box2d:1.11.0")
        "implementation"("org.yaml:snakeyaml:1.33")
       "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.8.2")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine")
    }
}
