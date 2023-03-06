package blackjack

import blackjack.controller.BlackJackController
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val blackJackController = BlackJackController(InputView(), OutputView())
    blackJackController.run()
}
