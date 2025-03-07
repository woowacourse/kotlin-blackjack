package blackjack.model.domain

class YesOrNo(val input: String) {
    init {
        require(input in yesValidInput + noValidInput) { ERROR_WRONG_FORMAT }
    }

    companion object {
        val yesValidInput: List<String> = listOf("y", "Y")
        val noValidInput: List<String> = listOf("n", "N")

        private const val ERROR_WRONG_FORMAT: String = "[ERROR] y와 n만 입력 가능합니다"
    }
}
