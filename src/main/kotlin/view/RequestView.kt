package view

class RequestView {

    fun requestInputNames(): List<String> {
        println(NAME_INPUT_SCRIPT)
        val input = readln()
        if (input.isBlank()) {
            println(ERROR_INPUT_BLACK)
            return requestInputNames()
        }
        return input.split(',').map { it.trim() }
    }

    fun isMoreCard(name: String): Boolean {
        println(ONE_MORE_CARD_SCRIPT.format(name))
        return when (readln().trim()) {
            "y" -> true
            "n" -> false
            else -> isMoreCard(name)
        }
    }

    fun requestBettingMoneys(name: String): Int {
        println(BETTING_SCRIPT.format(name))
        return readln().toIntOrNull() ?: requestBettingMoneys(name)
    }

    companion object {
        private const val ERROR_INPUT_BLACK = "공백은 입력할 수 없습니다."
        private const val NAME_INPUT_SCRIPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ONE_MORE_CARD_SCRIPT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val BETTING_SCRIPT = "%s의 배팅 금액은?"
    }
}
