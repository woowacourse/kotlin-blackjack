package blackjack.model.user

class DrawDecision(private val decision: String) {
    init {
        require(decision == YES || decision == NO) { ERROR_MESSAGE_INVALID_DECISION }
    }

    fun judgeDecision(): Boolean {
        return decision == YES
    }

    companion object {
        private const val ERROR_MESSAGE_INVALID_DECISION = "[ERROR] 카드를 더 받으시려면 y, 아니면 n을 입력해주세요."
        private const val YES = "y"
        private const val NO = "n"
    }
}
