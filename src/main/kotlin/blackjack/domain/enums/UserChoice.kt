package blackjack.domain.enums

enum class UserChoice {
    HIT,
    STAY,
    ;

    companion object {
        private const val INPUT_HIT = "y"
        private const val INPUT_STAY = "n"
        private const val INVALID_VALUE = "잘못된 값을 입력하였습니다."

        private val CHOICE = mutableMapOf<String, UserChoice>()

        fun from(value: String): UserChoice =
            CHOICE.computeIfAbsent(value) {
                when (value) {
                    INPUT_HIT -> HIT
                    INPUT_STAY -> STAY
                    else -> throw IllegalArgumentException(INVALID_VALUE)
                }
            }
    }
}
