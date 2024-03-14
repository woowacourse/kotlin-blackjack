package blackjack.view

import blackjack.model.Betting

class InputView {
    fun fetchPlayerNames(): List<String> {
        println(MESSAGE_INPUT_PLAYERS_NAME)
        return readln().split(DELIMITER).map { it.trim() }
    }

    fun fetchBetting(name: String): Betting {
        println(MESSAGE_INPUT_BETTING.format(name))
        val betting =
            readln().toIntOrNull() ?: run {
                println(ERROR_BETTING_INPUT)
                return fetchBetting(name)
            }
        return Betting(betting)
    }

    tailrec fun determineHit(name: String): Boolean {
        println(MESSAGE_INPUT_WHETHER_HIT.format(name))
        val input = readln()
        if (input == HIT) {
            return true
        }
        if (input == STAY) {
            return false
        }
        println(MESSAGE_INVALID_INPUT)
        return determineHit(name)
    }

    companion object {
        private const val ERROR_BETTING_INPUT = "정수를 입력해주세요"
        private const val DELIMITER = ","
        private const val MESSAGE_INVALID_INPUT = "y or n 로 다시 입력해주세요."
        private const val MESSAGE_INPUT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val MESSAGE_INPUT_BETTING = "%s의 배팅 금액은?"
        private const val HIT = "y"
        private const val STAY = "n"
        private const val MESSAGE_INPUT_WHETHER_HIT = "\n%s는 한장의 카드를 더 받겠습니까?(예는 $HIT, 아니오는 $STAY)"
    }
}
