package blackjack

import blackjack.controller.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val blackjackGame = BlackjackGame(inputView, outputView)
    blackjackGame.start()
}
