package blackjack.view

import blackjack.domain.model.Player

class InputView {
    private val outputView = OutputView()

    fun readPlayerNames(): List<String> {
        outputView.requestPlayerNames()
        val input: String = readln()
        return input.split(PLAYER_NAMES_DELIMITER).map { name: String -> name.trim() }
    }

    fun readPlayerAction(player: Player): String {
        outputView.requestPlayerAction(player)
        val input: String = readln()
        return input
    }

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
    }
}
