package blackjack.domain.model

class Choice(private val value: String) {
    init {
        require(value == CHOICE_YES || value == CHOICE_NO) { ERROR_INVALID_CHOICE }
    }

    fun isHit(): Boolean {
        return value == CHOICE_YES
    }

    companion object {
        private const val CHOICE_YES = "y"
        private const val CHOICE_NO = "n"

        private const val ERROR_INVALID_CHOICE = "y 또는 n을 입력해주세요."
    }
}
