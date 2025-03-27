package blackjack

import blackjack.controller.BlackjackController
import blackjack.view.BlackjackView

fun main() {
    val blackjackController = BlackjackController(BlackjackView())
    blackjackController.run()
}
