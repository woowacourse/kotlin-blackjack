package blackjack.view

class InputView {
    fun getNames(): List<String> {
        val input = readlnOrNull() ?: ""
        validateInput(input)
        return input.split(DELIMITER).map { it.trim() }
    }

    fun getFlag(): Boolean {
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
        private const val DELIMITER = ","
        private const val YES = "y"
        private const val NO = "n"
        private const val INVALID_FLAG_MESSAGE = "$YES 혹은 ${NO}을 입력해주세요."
        private const val INVALID_INPUT_MESSAGE = "입력이 비어있습니다."
    }
}
