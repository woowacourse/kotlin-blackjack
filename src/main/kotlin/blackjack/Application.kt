package blackjack

import blackjack.controller.BlackjackController

fun main() {
    Thread.setDefaultUncaughtExceptionHandler { _, e ->
        println(e.message)
        e.printStackTrace()
    }
    val blackjackController = BlackjackController()
    blackjackController.run()
}
