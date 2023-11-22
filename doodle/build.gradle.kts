plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.7.21"
    application
}

version = "1.0.0"
group   = "com.my.cool.app"



repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

kotlin {
    target.compilations.all {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    val doodleVersion = "0.9.3" // <--- Latest Doodle version

    dependencies {
        val osName = System.getProperty("os.name")
        val targetOs = when {
            osName == "Mac OS X"       -> "macos"
            osName.startsWith("Win"  ) -> "windows"
            osName.startsWith("Linux") -> "linux"
            else                       -> error("Unsupported OS: $osName")
        }

        val osArch = System.getProperty("os.arch")
        val targetArch = when (osArch) {
            "x86_64", "amd64" -> "x64"
            "aarch64"         -> "arm64"
            else              -> error("Unsupported arch: $osArch")
        }

        val target = "$targetOs-$targetArch"

        implementation ("io.nacular.doodle:core:$doodleVersion"               )
        implementation ("io.nacular.doodle:desktop-jvm-$target:$doodleVersion") // Desktop apps are tied to specific platforms

        // Optional
        // implementation ("io.nacular.doodle:controls:$doodleVersion" )
        // implementation ("io.nacular.doodle:animation:$doodleVersion")
        // implementation ("io.nacular.doodle:themes:$doodleVersion"   )
    }
}

application {
    mainClass.set("UsefulAppKt")
}