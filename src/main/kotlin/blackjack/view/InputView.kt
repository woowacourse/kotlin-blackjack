package blackjack.view

import blackjack.model.Player

class InputView {
    fun readPlayersName(): List<String> {
        println(READ_PLAYERS_NAME_MESSAGE)
        return readln().split(PLAYERS_NAME_DELIMITER)
    }

    tailrec fun readMoreCardDecision(player: Player): Boolean {
        println(READ_MESSAGE.format(player.name))
        val moreCardDecision = readln()
        return getMoreCardDecision(moreCardDecision) ?: readMoreCardDecision(player)
    }

    private fun getMoreCardDecision(moreCardDecision: String): Boolean? {
        if (moreCardDecision == MORE_CARD_MESSAGE) return true
        if (moreCardDecision == NO_MORE_CARD_MESSAGE) return false
        return null
    }

    companion object {
        private const val PLAYERS_NAME_DELIMITER = ","
        private const val READ_PLAYERS_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val READ_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val MORE_CARD_MESSAGE = "y"
        private const val NO_MORE_CARD_MESSAGE = "n"
    }
}
