package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    GameController(
        InputView,
        OutputView,
    ).run()
}
