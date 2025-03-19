package blackjack

import blackjack.controller.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val blackjackGame = BlackjackGame(InputView(), OutputView())
    blackjackGame.run()
}
