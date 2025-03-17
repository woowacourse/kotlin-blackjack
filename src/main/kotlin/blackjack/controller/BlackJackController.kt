package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.view.BlackJackInputView
import blackjack.view.BlackJackOutputView

class BlackJackController(
    private val inputView: BlackJackInputView,
    private val outputView: BlackJackOutputView,
) {
    fun play() {
        BlackJackGame(inputView, outputView).play()
    }
}
