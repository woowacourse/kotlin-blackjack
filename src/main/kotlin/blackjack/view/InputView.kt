package blackjack.view

class InputView {
    fun readPlayerNames(): List<String> {
        println(ALERT_PLAYER_NAME_INPUT)
        val rawInput: String = readln()
        return rawInput.split(",").map { it.trim() }
    }

    fun readWantExtraCard(name: String): Boolean {
        println(ALERT_READ_WANT_EXTRA_CARD.format(name))
        val rawInput: String = readln()
        return rawInput.toBooleanOrNull() ?: run {
            println(ERROR_WRONG_WANT_EXTRA_CARD_INPUT)
            readWantExtraCard(name)
        }
    }

    companion object {
        private const val ALERT_PLAYER_NAME_INPUT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ALERT_READ_WANT_EXTRA_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

        private const val ERROR_WRONG_WANT_EXTRA_CARD_INPUT = "잘못된 입력입니다 Y 또는 N만 입력해 주세요"

        private fun String.toBooleanOrNull(): Boolean? =
            when (this.uppercase()) {
                "Y" -> true
                "N" -> false
                else -> null
            }
    }
}
