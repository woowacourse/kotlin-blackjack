package blackjack.model.user

enum class UserDecision(
    private val userDecision: String,
) {
    YES("y"),
    NO("n"), ;

    companion object {
        private const val ERROR_DECISION = "잘못 된 결정입니다."

        fun getUserDecision(input: String): UserDecision {
            return UserDecision.entries.find {
                it.userDecision == input
            } ?: throw IllegalArgumentException(ERROR_DECISION)
        }
    }
}
