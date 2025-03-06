package blackjack.model

enum class CardsStatus(
    val firstTurn: Boolean,
) {
    BLACKJACK(true),
    BUST(false),
    NONE(false),
    ;

    companion object {
        fun from(
            cardsScore: Int,
            firstTurn: Boolean,
        ): CardsStatus {
            if (firstTurn && cardsScore == 21) return BLACKJACK
            if (cardsScore > 21) return BUST
            return NONE
        }
    }
}
