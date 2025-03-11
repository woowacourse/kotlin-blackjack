package blackjack

import blackjack.controller.BlackjackController
import blackjack.model.GameManager
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val gameManager = GameManager()
    val inputView = InputView()
    val outputView = OutputView()

    BlackjackController(gameManager, inputView, outputView).run()
}
