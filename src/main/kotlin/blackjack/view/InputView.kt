package blackjack.view

import blackjack.domain.model.Action
import blackjack.domain.model.Player
import java.lang.IllegalArgumentException

class InputView {
    private val outputView = OutputView()

    fun readPlayerNames(): List<String> {
        outputView.requestPlayerNames()
        val input: String = readInput()
        return input.split(PLAYER_NAMES_DELIMITER).map { name: String -> name.trim() }
    }

    fun readPlayerAction(player: Player): Action {
        outputView.requestPlayerAction(player)
        val input: String = readInput().lowercase()
        return when (input) {
            "y" -> Action.HIT
            "n" -> Action.STAND
            else -> throw IllegalArgumentException(ERROR_MESSAGE_INVALID_CHOICE)
        }
    }

    private fun readInput(): String {
        val input: String = readln()
        outputView.printInputDivider()
        return input
    }

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
        private const val ERROR_MESSAGE_INVALID_CHOICE = "y 또는 n을 입력해주세요."
    }
}
