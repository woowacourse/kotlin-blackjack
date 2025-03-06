package blackjack.view

import blackjack.Player

class InputView {
    fun readPlayerNames(): List<Player> {
        println(PLAYER_NAME_MESSAGE_GUIDE)
        val playerNames: List<String> = readln().split(PLAYER_NAME_DELIMITER).map { name -> name.trim() }
        if (playerNames.size != playerNames.toSet().size) return readPlayerNames()
        val players = playerNames.map { playerName -> Player(playerName) }
        return players
    }

    companion object {
        private const val PLAYER_NAME_MESSAGE_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val PLAYER_NAME_DELIMITER = ","
    }
}
