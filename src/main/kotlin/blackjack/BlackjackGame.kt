package blackjack

import blackjack.controller.BlackjackController
import blackjack.model.Dealer
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val blackjackController = BlackjackController(InputView(), OutputView())
    blackjackController.play(dealer = Dealer())
}
