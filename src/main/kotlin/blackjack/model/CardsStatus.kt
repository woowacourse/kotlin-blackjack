package blackjack.model

enum class CardsStatus {
    BLACKJACK,
    BUST,
    NONE,
    ;

    companion object {
        const val BLACKJACK_SCORE = 21

        fun from(
            cardsScore: Int,
            firstTurn: Boolean = false,
        ): CardsStatus {
            if (firstTurn && cardsScore == BLACKJACK_SCORE) return BLACKJACK
            if (cardsScore > BLACKJACK_SCORE) return BUST
            return NONE
        }
    }
}
