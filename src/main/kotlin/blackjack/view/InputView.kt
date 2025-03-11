package blackjack.view

class InputView {
    fun getNames(): List<String> {
        println(ENTER_PLAYER_NAMES_MESSAGE)
        val input = readlnOrNull() ?: ""
        validateInput(input)
        return input.split(DELIMITER).map { it.trim() }
    }

    fun getHitFlag(name: String): Boolean {
        println(ASK_DRAW_CARD_MESSAGE.format(name))
        val input = readlnOrNull() ?: ""
        validateInput(input)
        require(input == YES || input == NO) { INVALID_FLAG_MESSAGE }
        return input == YES
    }

    private fun validateInput(input: String) {
        require(input.isNotBlank()) {
            INVALID_INPUT_MESSAGE
        }
    }

    companion object {
        private const val ENTER_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ASK_DRAW_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
        private const val DELIMITER = ","
        private const val YES = "y"
        private const val NO = "n"
        private const val INVALID_FLAG_MESSAGE = "$YES 혹은 ${NO}을 입력해주세요."
        private const val INVALID_INPUT_MESSAGE = "입력이 비어있습니다."
    }
}
