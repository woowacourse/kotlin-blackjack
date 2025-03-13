package blackjack.view

import blackjack.model.participant.Player

class InputView {
    fun readPlayerNames(): List<String>? {
        println(PLAYER_NAME_MESSAGE_GUIDE)
        val playerNames: List<String> = readln().split(PLAYER_NAME_DELIMITER).map { name -> name.trim() }

        if (playerNames.any { it.isEmpty() }) {
            println(ERROR_PLAYER_NAME_EMPTY)
            return null
        }

        return playerNames
    }

    fun readBetAmount(playerName: String): Int? {
        println(PLAYER_BET_AMOUNT_MESSAGE_GUIDE.format(playerName))
        val betAmount = readln().trim()

        if (betAmount.toIntOrNull() == null) {
            println(ERROR_BET_AMOUNT_TYPE)
            return null
        }

        return betAmount.toInt()
    }

    fun readMoreCardCondition(player: Player): String {
        println(PLAYER_MORE_CARD_MESSAGE_GUIDE.format(player.name))
        val condition: String = readln().trim()
        return condition
    }

    companion object {
        private const val PLAYER_NAME_MESSAGE_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ERROR_PLAYER_NAME_EMPTY = "플레이어 이름은 비어있으면 안 됩니다. 다시 입력해주세요."
        private const val PLAYER_BET_AMOUNT_MESSAGE_GUIDE = "%s의 배팅 금액은?"
        private const val ERROR_BET_AMOUNT_TYPE = "배팅 금액은 정수를 입력해야 합니다. 다시 입력해주세요."
        private const val PLAYER_MORE_CARD_MESSAGE_GUIDE = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val PLAYER_NAME_DELIMITER = ","
    }
}
