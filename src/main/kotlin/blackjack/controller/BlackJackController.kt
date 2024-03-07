package blackjack.controller

import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    val inputView: InputView,
    val outputView: OutputView,
) {
    fun startGame() {
        val playerNames = getPlayerNames()
    }

    private fun getPlayerNames(): List<String> {
        return runCatching {
            inputView.readPlayerNames() ?: getPlayerNames()
        }.onFailure {
            println(it.message)
            return getPlayerNames()
        }.getOrThrow()
    }
}
