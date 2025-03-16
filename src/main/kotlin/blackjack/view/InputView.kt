package blackjack.view

class InputView {
    fun readPlayerNames(): List<String> {
        println(ALERT_PLAYER_NAME_INPUT)
        val rawInput: String = readln()
        return rawInput.split(",").map { it.trim() }
    }

    fun readBetAmount(name: String): Int {
        lineSeparator()
        println(ALERT_READ_BET_AMOUNT.format(name))
        val rawInput: String = readln()
        return rawInput.toIntOrNull() ?: run {
            println(ERROR_CANT_PARSE_INT)
            readBetAmount(name)
        }
    }

    fun readWantExtraCard(name: String): Boolean {
        println(ALERT_READ_WANT_EXTRA_CARD.format(name))
        val rawInput: String = readln()
        return rawInput.toBooleanOrNull() ?: run {
            println(ERROR_WRONG_WANT_EXTRA_CARD_INPUT)
            readWantExtraCard(name)
        }
    }

    private fun lineSeparator() {
        println()
    }

    companion object {
        private const val ALERT_PLAYER_NAME_INPUT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ALERT_READ_BET_AMOUNT = "%s의 배팅 금액은?"
        private const val ALERT_READ_WANT_EXTRA_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

        private const val ERROR_WRONG_WANT_EXTRA_CARD_INPUT = "잘못된 입력입니다 Y 또는 N만 입력해 주세요"
        private const val ERROR_CANT_PARSE_INT = "정수로 변환할 수 없는 입력값입니다. 다시 입력해주세요"

        private fun String.toBooleanOrNull(): Boolean? =
            when (this.uppercase()) {
                "Y" -> true
                "N" -> false
                else -> null
            }
    }
}
