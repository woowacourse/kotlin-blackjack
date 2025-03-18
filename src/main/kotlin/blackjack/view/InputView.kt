package blackjack.view

class InputView {
    fun readPlayerName(): List<String> {
        while (true) {
            println(MESSAGE_INPUT_PLAYER_NAME)
            val input = readln()
            if (validEmpty(input).not()) readPlayerName()

            val names = splitNames(input)
            if (validateEmptyList(names).not()) readPlayerName()
            return names
        }
    }

    fun readBettingAmount(name: String): Int = readInt(MESSAGE_INPUT_BETTING_MONEY.format(name))

    private fun readInt(message: String): Int {
        while (true) {
            println(message)
            val input = readln()
            val number = validateAndParseInt(input)
            if (number != null) return number
        }
    }

    fun readHitOrStay(name: String): String {
        println(MESSAGE_ASK_PLAYER_HIT_OR_STAY.format(name))
        val input = readln()
        if (validEmpty(input).not()) readHitOrStay(name)
        return input
    }

    private fun splitNames(input: String): List<String> = input.split(",").map { it.trim() }

    private fun validEmpty(input: String): Boolean {
        if (input.isBlank()) {
            println(MESSAGE_EMPTY_INPUT)
            return false
        }
        return true
    }

    private fun validateAndParseInt(input: String): Int? {
        if (validEmpty(input).not()) return null
        val value = input.toIntOrNull()
        if (value == null) {
            println(NOT_NUMERIC_ERROR)
        }
        return value
    }

    private fun validateEmptyList(names: List<String>): Boolean {
        if (names.any { it.isBlank() }) {
            println(MESSAGE_EMPTY_NAME)
            return false
        }
        return true
    }

    companion object {
        private const val MESSAGE_INPUT_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val MESSAGE_ASK_PLAYER_HIT_OR_STAY = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val MESSAGE_INPUT_BETTING_MONEY = "%s의 배팅 금액은?"

        private const val MESSAGE_EMPTY_INPUT = "빈 값이 입력 되었습니다."
        private const val MESSAGE_EMPTY_NAME = "빈 이름이 포함되어 있습니다."
        private const val NOT_NUMERIC_ERROR = "숫자만 입력해주세요."
    }
}
