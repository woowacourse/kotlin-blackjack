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
        require(input == "y" || input == "n") { INVALID_FLAG_MESSAGE }
        return input == "y"
    }

    private fun validateInput(input: String) {
        require(input.isNotBlank()) {
            INVALID_INPUT_MESSAGE
        }
    }

    companion object {
        private const val DELIMITER = ","
        private const val INVALID_FLAG_MESSAGE = "y 혹은 n을 입력해주세요."
        private const val INVALID_INPUT_MESSAGE = "입력이 비어있습니다."
    }
}
