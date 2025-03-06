package blackjack.model

enum class PlayerBehavior(
    private val answer: String,
) {
    HIT("Y"),
    STAY("N"),
    ;

    companion object {
        fun from(answer: String): PlayerBehavior =
            when (answer) {
                "Y" -> HIT
                "N" -> STAY
                else -> throw IllegalArgumentException("ddd")
            }

        fun from(dealerScore: Int): PlayerBehavior {
            if (dealerScore <= 16) return HIT
            return STAY
        }
    }
}
