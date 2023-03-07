package view

class LoginView {

    fun requestPlayerName(): List<String> {
        printRequestPlayerName()

        return readLine()?.split(SEPARATOR)?.map { name ->
            name.trim()
        } ?: emptyList()
    }

    private fun printRequestPlayerName() {
        println(REQUEST_PLAYER_NAME)
    }

    fun requestBetAmount(name: String): Int {
        println(REQUEST_BET_AMOUNT.format(name))
        val input = readln().toIntOrNull() ?: notNumberBetAmount(name)
        if (input <= 0) negativeBetAmount(name)
        return input
    }

    private fun notNumberBetAmount(name: String): Int {
        println(NOT_NUMBER)
        return requestBetAmount(name)
    }

    private fun negativeBetAmount(name: String): Int {
        println(NEGATIVE_NUMBER)
        return requestBetAmount(name)
    }

    companion object {
        private const val REQUEST_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val REQUEST_BET_AMOUNT = "%s의 배팅 금액은?"
        private const val NOT_NUMBER = "숫자가 아닙니다."
        private const val NEGATIVE_NUMBER = "음수입니다."
        private const val SEPARATOR = ","
    }
}
