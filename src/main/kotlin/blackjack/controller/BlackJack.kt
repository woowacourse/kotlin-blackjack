package blackjack.controller

import blackjack.view.InputView

class BlackJack(
    private val inputView: InputView,
) {
    fun gameStart() {
        println(inputView.readPlayersName())
    }
}
