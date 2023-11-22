import io.nacular.doodle.application.Application
import io.nacular.doodle.application.application

class UsefulApp: Application {
    init {
        println("Hi!")
    }

    override fun shutdown() {}
}


fun main() {
    // launch full-screen
    application {
        UsefulApp()
    }
}
