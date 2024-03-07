package blackjack

import blackjack.controller.BlackjackController
import blackjack.model.RandomCardProvider
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val blackjackController =
        BlackjackController(
            InputView(),
            OutputView(),
            RandomCardProvider,
        )
    blackjackController.run()
}
