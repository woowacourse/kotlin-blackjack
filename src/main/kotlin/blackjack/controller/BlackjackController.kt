package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.view.BlackjackInput
import blackjack.view.BlackjackOutput

class BlackjackController(
    private val inputView: BlackjackInput,
    private val outputView: BlackjackOutput,
) {
    fun play() {
        val blackjackGame = BlackjackGame(inputView = inputView, outputView = outputView)
        blackjackGame.start()
    }
}
