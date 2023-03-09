package view

class GameSetView {
    fun readPlayerNames(): List<String> {
        println(MESSAGE_READ_PLAYER_NAME)
        val input = readln().trim()
        require(input.contains(",")) { NAME_INPUT_ERROR_MESSAGE }
        return input.split(",")
    }

    fun readBettingMoney(name: String): Int {
        println(MESSAGE_READ_BETTING_MONEY.format(name))
        return readln().trim().toInt()
    }

    companion object {
        private const val MESSAGE_READ_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val NAME_INPUT_ERROR_MESSAGE = ",를 이용해 사용자를 구분하여야 합니다."

        private const val MESSAGE_READ_BETTING_MONEY = "%s의 배팅 금액은?"
    }
}
