package blackjack.view

class InputView {
    fun readPlayerName() {
        while (true) {
            println(MESSAGE_INPUT_PLAYER_NAME)
            val input = readln()
            if (validEmpty(input)) readPlayerName()

            val players = input.split(",").map { it.trim().also { validEmpty(it) } }
        }
    }

    private fun validEmpty(input: String): Boolean {
        if (input.isBlank()) {
            println(MESSAGE_EMPTY_INPUT)
            return false
        }
        return true
    }

    companion object {
        const val MESSAGE_INPUT_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        const val MESSAGE_EMPTY_INPUT = "빈 값이 입력되었습니다."
    }
}
