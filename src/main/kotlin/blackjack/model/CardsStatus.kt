package blackjack.model

enum class CardsStatus {
    BLACKJACK,
    BUST,
    NONE,
    ;

    companion object {
        const val BLACKJACK_SCORE = 21
        const val BUST_SCORE = 0

        fun from(
            cardsScore: Int,
            cardsSize: Int,
        ): CardsStatus {
            if (cardsSize == 2 && cardsScore == BLACKJACK_SCORE) return BLACKJACK
            if (cardsScore > BLACKJACK_SCORE) return BUST
            return NONE
        }
    }
}
