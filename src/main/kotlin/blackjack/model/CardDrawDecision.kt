package blackjack.model

enum class CardDrawDecision(val response: String) {
    YES("y"),
    NO("n"),
    ;

    fun isDraw(): Boolean {
        return this == YES
    }

    companion object {
        fun from(response: String): CardDrawDecision? {
            return entries.find { decision -> decision.response == response }
        }
    }
}
