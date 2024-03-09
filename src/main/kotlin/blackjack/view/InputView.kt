package blackjack.view

import blackjack.model.participant.Player

class InputView {
    fun inputPlayerNames(): List<String> {
        println(MESSAGE_INPUT_PLAYERS_NAME)
        return readln().split(DELIMITER).map { it.trim() }
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
        println(MESSAGE_INVALID_INPUT)
        return inputWhetherHit(player)
    }

    companion object {
        private const val DELIMITER = ","
        private const val MESSAGE_INVALID_INPUT = "y or n 로 다시 입력해주세요."
        private const val MESSAGE_INPUT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val HIT = "y"
        private const val STAY = "n"
        private const val MESSAGE_INPUT_WHETHER_HIT = "\n%s는 한장의 카드를 더 받겠습니까?(예는 $HIT, 아니오는 $STAY)"
    }
}
