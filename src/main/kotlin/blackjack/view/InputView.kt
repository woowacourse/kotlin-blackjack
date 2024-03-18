package blackjack.view

import blackjack.model.Player

class InputView {
    fun inputPlayerNames(): List<String> {
        println(MESSAGE_INPUT_PLAYERS_NAME)
        return readln().split(DELIMITER).map { it.trim() }
    }

    fun inputPlayerBetAmount(playerNames: List<String>): MutableList<Int> {
        val playersBetAmounts: MutableList<Int> = mutableListOf()
        playerNames.forEach { playerName ->
            println(MESSAGE_INPUT_PLAYERS_BUDGET.format(playerName))
            playersBetAmounts.add(readln().toInt())
        }
        return playersBetAmounts
    }

    tailrec fun inputWhetherHit(player: Player): Boolean {
        println(MESSAGE_INPUT_WHETHER_HIT.format(player.name))
        val input = readln()
        if (input == HIT) {
            return true
        }
        if (input == STAY) {
            return false
        }
        return inputWhetherHit(player)
    }

    companion object {
        private const val MESSAGE_INPUT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val MESSAGE_INPUT_PLAYERS_BUDGET = "%s의 베팅 금액은?"
        private const val HIT = "y"
        private const val STAY = "n"
        private const val MESSAGE_INPUT_WHETHER_HIT = "\n%s는 한장의 카드를 더 받겠습니까?(예는 $HIT, 아니오는 $STAY)"
        private const val DELIMITER = ","
    }
}
