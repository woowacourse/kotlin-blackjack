package blackjack.view

import blackjack.model.participant.BattingAmount
import blackjack.model.participant.Player
import blackjack.model.participant.PlayerName

class InputView {
    fun readPlayersName(): List<PlayerName> {
        println(READ_PLAYERS_NAME_MESSAGE)
        return readln().split(PLAYERS_NAME_DELIMITER).map { PlayerName(it) }
    }

    fun readMoreCardDecision(player: Player): Boolean {
        println(READ_MORE_CARD_DECISION_MESSAGE.format(player.name))
        val moreCardDecision = readln()
        return getMoreCardDecision(moreCardDecision) ?: run {
            println(INVALID_MORE_CARD_DECISION_MESSAGE)
            readMoreCardDecision(player)
        }
    }

    fun readPlayersBattingAmount(playersName: List<PlayerName>): List<BattingAmount> {
        val playersBattingAmount = mutableListOf<BattingAmount>()
        playersName.forEach { playerName ->
            println(READ_PLAYER_BATTING_AMOUNT_MESSAGE.format(playerName))
            playersBattingAmount.add(BattingAmount(readPlayerBattingAmount(playerName)))
        }
        return playersBattingAmount
    }

    private fun readPlayerBattingAmount(playerName: PlayerName): Int {
        val battingAmountValue = readln()
        return battingAmountValue.toIntOrNull() ?: run {
            println(INVALID_BATTING_AMOUNT_MESSAGE)
            readPlayerBattingAmount(playerName)
        }
    }

    private fun getMoreCardDecision(moreCardDecision: String): Boolean? {
        if (moreCardDecision == MORE_CARD_MESSAGE) return true
        if (moreCardDecision == NO_MORE_CARD_MESSAGE) return false
        return null
    }

    companion object {
        private const val PLAYERS_NAME_DELIMITER = ","
        private const val READ_PLAYERS_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val READ_PLAYER_BATTING_AMOUNT_MESSAGE = "%s의 배팅 금액은?"
        private const val INVALID_BATTING_AMOUNT_MESSAGE = "배팅금액은 숫자로 입력해 주세요."
        private const val READ_MORE_CARD_DECISION_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val INVALID_MORE_CARD_DECISION_MESSAGE = "소문자 y 또는 n으로 입력해 주세요."
        private const val MORE_CARD_MESSAGE = "y"
        private const val NO_MORE_CARD_MESSAGE = "n"
    }
}
