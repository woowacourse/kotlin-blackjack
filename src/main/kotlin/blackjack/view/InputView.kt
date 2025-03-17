package blackjack.view

import blackjack.domain.model.hand.UserChoice
import blackjack.domain.model.playing.PlayingParticipant

class InputView {
    fun readPlayerNames(): Set<String> {
        println(MESSAGE_ENTER_PLAYER_NAMES)
        val input: String = readln()
        return input.split(PLAYER_NAMES_DELIMITER).map { name: String -> name.trim() }.toSet()
    }

    fun readPlayerBetAmount(playerName: String): Double {
        println("${playerName}의 배팅 금액은?")
        return requireNotNull(readln().toDoubleOrNull()) { "배팅 금액은 소수를 입력해주세요" }
    }

    fun readPlayerAction(player: PlayingParticipant): UserChoice {
        println(MESSAGE_ENTER_PLAYER_YES_OR_NO.format(player.name))
        val input: String = readln()
        require(input == CHOICE_YES || input == CHOICE_NO) { ERROR_INVALID_CHOICE }
        return convertChoice(input)
    }

    private fun convertChoice(input: String): UserChoice {
        require(input == CHOICE_YES || input == CHOICE_NO) { ERROR_INVALID_CHOICE }
        if (input == CHOICE_YES) return UserChoice.HIT
        return UserChoice.STAY
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
