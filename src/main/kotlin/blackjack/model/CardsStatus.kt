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
            if (firstTurn && cardsScore == 21) return BLACKJACK
            if (cardsScore > 21) return BUST
            return NONE
        }
    }
}
