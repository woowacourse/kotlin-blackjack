package blackjack.view

class InputView {
    fun readPlayerNames(): List<String> {
        println(MESSAGE_INPUT_PLAYER_NAME)
        val input = readln().split(COMMA).map { it.trim() }.filter { it.isNotBlank() }
        if (input.isNotEmpty()) {
            return input
        }
        println(ERROR_INVALID_INPUT)
        return readPlayerNames()
    }

    companion object {
        private const val MESSAGE_INPUT_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ERROR_INVALID_INPUT = "올바르지 않은 입력입니다. 다시 입력해주세요."
        private const val COMMA = ","
    }
}
