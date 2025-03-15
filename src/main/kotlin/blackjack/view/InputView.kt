package blackjack.view

import blackjack.domain.Money

class InputView {
    fun readPlayerNames(): List<String> =
        runCatching {
            println(MESSAGE_INPUT_PLAYER_NAME)
            val input = readln().trim()

            require(input.isNotBlank()) { MESSAGE_EMPTY_INPUT }

            val names = input.split(",").map { it.trim() }
            require(names.any { it.isNotBlank() }) { MESSAGE_EMPTY_NAME }
            names
        }.getOrElse {
            println(it.message)
            readPlayerNames()
        }

    fun readHitOrStay(name: String): Boolean =
        runCatching {
            println(MESSAGE_ASK_PLAYER_HIT_OR_STAY.format(name))
            val input = readln().trim()

            require(input.isNotBlank()) { MESSAGE_EMPTY_INPUT }

            when (input) {
                "y" -> true
                "n" -> false
                else -> throw IllegalArgumentException(MESSAGE_WRONG_INPUT)
            }
        }.getOrElse {
            println(it.message)
            readHitOrStay(name)
        }

    fun readBettingMoney(name: String): Money =
        runCatching {
            println(MESSAGE_ASK_PLAYER_BET.format(name))
            val input = readln().trim()

            require(input.isNotBlank()) { MESSAGE_EMPTY_INPUT }
            require(input.toIntOrNull() != null) { MESSAGE_WRONG_INPUT }
            Money(input.toInt())
        }.getOrElse {
            println(it.message)
            readBettingMoney(name)
        }

    companion object {
        const val MESSAGE_INPUT_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        const val MESSAGE_ASK_PLAYER_HIT_OR_STAY = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        const val MESSAGE_ASK_PLAYER_BET = "%s의 배팅 금액은?"

        const val MESSAGE_EMPTY_INPUT = "[ERROR] 빈 값이 입력 되었습니다."
        const val MESSAGE_EMPTY_NAME = "[ERROR] 빈 이름이 포함되어 있습니다."
        const val MESSAGE_WRONG_INPUT = "[ERROR] 잘못된 값을 입력하였습니다."
    }
}
