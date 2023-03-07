package view

class InitView {
    fun readPlayerNames(): List<String> {
        println(MESSAGE_READ_PLAYER_NAME)
        val input = readln().trim()
        require(input.contains(",")) { MESSAGE_COMMA_NEEDED }
        return input.split(",")
    }

    fun readPlayerMoney(name: String): Int {
        println(MESSAGE_READ_PLAYER_MONEY.format(name))
        val input = readln().trim()
        requireNotNull(input.toIntOrNull()) { MESSAGE_ONLY_INTEGER }
        return input.toInt()
    }

    companion object {
        private const val MESSAGE_READ_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val MESSAGE_READ_PLAYER_MONEY = "%s의 배팅 금액은?"
        private const val MESSAGE_COMMA_NEEDED = ",를 이용해 사용자를 구분하여야 합니다."
        private const val MESSAGE_ONLY_INTEGER = "금액은 숫자만 입력할 수 있습니다."
    }
}
