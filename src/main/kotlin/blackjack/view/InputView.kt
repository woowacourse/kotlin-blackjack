package blackjack.view

import blackjack.domain.model.Choice
import blackjack.domain.model.Participant

class InputView {
    fun readPlayerNames(): List<String> {
        println(MESSAGE_ENTER_PLAYER_NAMES)
        val input: String = readln()
        return input.split(PLAYER_NAMES_DELIMITER).map { name: String -> name.trim() }
    }

    fun readPlayerAction(player: Participant): Choice {
        println(MESSAGE_ENTER_PLAYER_YES_OR_NO.format(player.name))
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
        private const val MESSAGE_ENTER_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val MESSAGE_ENTER_PLAYER_YES_OR_NO = "%s은(는) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
    }
}
