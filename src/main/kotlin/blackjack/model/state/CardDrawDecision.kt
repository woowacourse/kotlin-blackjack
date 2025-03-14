package blackjack.model.state

enum class CardDrawDecision(
    val response: String,
) {
    YES("y"),
    NO("n"),
    ;

    fun isDraw(): Boolean = this == YES

    companion object {
        fun from(response: String): CardDrawDecision? = entries.find { decision -> decision.response == response }
    }
}
