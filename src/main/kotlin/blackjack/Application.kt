package blackjack

import blackjack.domain.BlackJackController
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    BlackJackController(
        InputView,
        OutputView,
    ).run()
}
