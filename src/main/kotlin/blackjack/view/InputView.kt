package blackjack.view

import blackjack.model.DrawChoice
import blackjack.model.Player

class InputView {
    fun readPlayerNames(): List<Player> {
        println(PLAYER_NAME_MESSAGE_GUIDE)
        val playerNames: List<String> = readln().split(PLAYER_NAME_DELIMITER).map { name -> name.trim() }
        if (playerNames.size != playerNames.toSet().size) return readPlayerNames()
        val players = playerNames.map { playerName -> Player(playerName) }
        return players
    }

    fun readMoreCardCondition(player: Player): DrawChoice {
        println(PLAYER_MORE_CARD_MESSAGE_GUIDE.format(player.name))
        val condition: String = readln().trim()
        if (DrawChoice.contains(condition)) return readMoreCardCondition(player)
        return DrawChoice.from(condition)
    }

    companion object {
        private const val PLAYER_NAME_MESSAGE_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val PLAYER_MORE_CARD_MESSAGE_GUIDE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val PLAYER_NAME_DELIMITER = ","
    }
}
