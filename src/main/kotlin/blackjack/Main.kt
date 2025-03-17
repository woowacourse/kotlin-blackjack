package blackjack

import blackjack.controller.Controller
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val controller = Controller(inputView, outputView)
    controller.gameStart()
}
