package blackjack.domain

enum class UserChoice {
    HIT,
    STAY,
    ;

    companion object {
        private const val INPUT_HIT = "y"
        private const val INPUT_STAY = "n"
        private const val INVALID_VALUE = "잘못된 값을 입력하였습니다."

        fun from(value: String): UserChoice =
            when (value) {
                INPUT_HIT -> HIT
                INPUT_STAY -> STAY
                else -> throw IllegalArgumentException(INVALID_VALUE)
            }
    }
}
