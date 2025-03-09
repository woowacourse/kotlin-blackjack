package blackjack.view

import blackjack.domain.model.Choice
import blackjack.domain.model.Participant

class InputView {
    private val outputView = OutputView()

    fun readPlayerNames(): List<String> {
        outputView.requestPlayerNames()
        val input: String = readln()
        return input.split(PLAYER_NAMES_DELIMITER).map { name: String -> name.trim() }
    }

    fun readPlayerAction(player: Participant): Choice {
        outputView.requestPlayerAction(player)
        val input: String = readln()
        require(input == CHOICE_YES || input == CHOICE_NO) { ERROR_INVALID_CHOICE }
        return convertChoice(input)
    }

    private fun convertChoice(input: String): Choice {
        require(input == CHOICE_YES || input == CHOICE_NO) { ERROR_INVALID_CHOICE }
        if (input == CHOICE_YES) return Choice.YES
        return Choice.NO
    }

    companion object {
        private const val CHOICE_YES = "y"
        private const val CHOICE_NO = "n"
        private const val ERROR_INVALID_CHOICE = "y 또는 n을 입력해주세요."
        private const val PLAYER_NAMES_DELIMITER = ","
    }
}
