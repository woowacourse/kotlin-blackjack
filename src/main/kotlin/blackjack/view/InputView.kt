package blackjack.view

import blackjack.model.DrawChoice
import blackjack.model.Player

class InputView {
    fun readPlayerNames(): List<String> {
        println(PLAYER_NAME_MESSAGE_GUIDE)
        val playerNames: List<String> = readln().split(PLAYER_NAME_DELIMITER).map { name -> name.trim() }
        if (playerNames.size != playerNames.toSet().size) {
            println(ERROR_INVALID_PLAYER_NAMES)
            return readPlayerNames()
        }
        return playerNames
    }

    fun readMoreCardCondition(player: Player): String {
        println(PLAYER_MORE_CARD_MESSAGE_GUIDE.format(player.name))
        val condition: String = readln().trim()
        if (DrawChoice.from(condition) == null) {
            println(ERROR_INVALID_CARD_CONDITION)
            return readMoreCardCondition(player)
        }
        return condition
    }

    companion object {
        private const val PLAYER_NAME_MESSAGE_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ERROR_INVALID_PLAYER_NAMES = "중복된 이름이 있습니다. 다시 입력해주세요."
        private const val PLAYER_MORE_CARD_MESSAGE_GUIDE = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val ERROR_INVALID_CARD_CONDITION = "y나 n을 입력해야 합니다. 다시 입력해주세요."
        private const val PLAYER_NAME_DELIMITER = ","
    }
}
