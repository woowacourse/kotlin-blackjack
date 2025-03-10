package blackjack.model.domain

enum class ActionType {
    Hit,
    Stay,
    ;

    companion object {
        private val yesValidInput: List<String> = listOf("y", "Y")
        private val noValidInput: List<String> = listOf("n", "N")
        private const val ERROR_WRONG_FORMAT: String = "[ERROR] y와 n만 입력 가능합니다"

        fun get(yesOrNo: String): ActionType {
            validate(yesOrNo)
            if (yesOrNo in yesValidInput) return Hit
            return Stay
        }

        private fun validate(input: String) {
            require(input in yesValidInput + noValidInput) { ERROR_WRONG_FORMAT }
        }
    }
}
