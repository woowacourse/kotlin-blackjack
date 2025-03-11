package blackjack

import blackjack.controller.BlackjackController
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val controller = BlackjackController(InputView(), OutputView())

    controller.run()
}
