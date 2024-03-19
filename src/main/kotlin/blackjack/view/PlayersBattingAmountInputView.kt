package blackjack.view

class PlayersBattingAmountInputView {
    fun read(playersName: List<String>): List<Int> {
        return playersName.map { readPlayerBattingAmount(it) }
    }

    private fun readPlayerBattingAmount(name: String): Int {
        println(READ_MESSAGE.format(name))
        return readln().toUIntOrNull()?.toInt() ?: run {
            println(INVALID_MESSAGE)
            readPlayerBattingAmount(name)
        }
    }

    companion object {
        private const val READ_MESSAGE = "%s의 배팅 금액은?"
        private const val INVALID_MESSAGE = "0보다 큰 숫자만 입력할 수 있습니다."
    }
}
