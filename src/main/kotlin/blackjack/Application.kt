package blackjack

import blackjack.controller.BlackjackController
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val controller = BlackjackController(inputView, outputView)
    controller.play()
}
