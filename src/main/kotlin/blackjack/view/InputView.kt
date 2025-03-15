package blackjack.view

import blackjack.domain.model.Action
import blackjack.domain.model.participant.Player

class InputView {
    fun readPlayerNames(): List<String> {
        println(MESSAGE_REQUEST_PLAYER_NAMES)
        val input: String = readInput()
        return input.split(PLAYER_NAMES_DELIMITER).map { name: String -> name.trim() }
    }

    fun readPlayerAction(player: Player): Action {
        println(MESSAGE_REQUEST_PLAYER_HIT_OR_STAY.format(player.name))
        val input: String = readInput().lowercase()
        return convertToAction(input)
    }

    fun readPlayerBet(player: Player): Int {
        println(MESSAGE_REQUEST_PLAYER_BET.format(player.name))
        val amount: Int = readInput().toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_NOT_A_NUMBER)
        return amount
    }

    private fun convertToAction(input: String): Action {
        return when (input) {
            PLAYER_ACTION_YES -> Action.HIT
            PLAYER_ACTION_NO -> Action.STAND
            else -> throw IllegalArgumentException(ERROR_MESSAGE_INVALID_CHOICE)
        }
    }

    private fun readInput(): String {
        val input: String = readln()
        println()
        return input
    }

    companion object {
        private const val MESSAGE_REQUEST_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val MESSAGE_REQUEST_PLAYER_HIT_OR_STAY = "%s은(는) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
        private const val MESSAGE_REQUEST_PLAYER_BET = "%s의 배팅 금액은?"

        private const val ERROR_MESSAGE_NOT_A_NUMBER = "숫자를 입력해주세요."
        private const val ERROR_MESSAGE_INVALID_CHOICE = "y 또는 n을 입력해주세요."

        private const val PLAYER_ACTION_YES = "y"
        private const val PLAYER_ACTION_NO = "n"
        private const val PLAYER_NAMES_DELIMITER = ","
    }
}
