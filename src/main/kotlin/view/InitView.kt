package view

class InitView(private val input: Input, private val output: Output) {
    fun readPlayerNames(): List<String> {
        output.println(MESSAGE_READ_PLAYER_NAME)
        val trimmedInput = input.readln().trim()
        require(trimmedInput.contains(",")) { MESSAGE_COMMA_NEEDED }
        return trimmedInput.split(",")
    }

    fun readPlayerMoney(name: String): Int {
        output.println(MESSAGE_READ_PLAYER_MONEY.format(name))
        val trimmedInput = input.readln().trim()
        requireNotNull(trimmedInput.toIntOrNull()) { MESSAGE_ONLY_INTEGER }
        return trimmedInput.toInt()
    }

    companion object {
        private const val MESSAGE_READ_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val MESSAGE_READ_PLAYER_MONEY = "%s의 배팅 금액은?"
        private const val MESSAGE_COMMA_NEEDED = ",를 이용해 사용자를 구분하여야 합니다."
        private const val MESSAGE_ONLY_INTEGER = "금액은 숫자만 입력할 수 있습니다."
    }
}
