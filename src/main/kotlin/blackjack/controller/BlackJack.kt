package blackjack.controller

import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJack(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun gameStart() {
        inputView.readPlayersName()
    }
}
