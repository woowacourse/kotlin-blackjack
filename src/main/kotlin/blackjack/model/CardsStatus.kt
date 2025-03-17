package blackjack.model

enum class CardsStatus {
    BLACKJACK,
    BUST,
    NONE,
    ;

    companion object {
        const val BLACKJACK_SCORE = 21
        const val BUST_SCORE = 0
        const val FIRST_TURN_CARD_COUNT = 2

        fun from(
            cardsScore: Int,
            cardsSize: Int,
        ): CardsStatus {
            if (cardsSize == FIRST_TURN_CARD_COUNT && cardsScore == BLACKJACK_SCORE) return BLACKJACK
            if (cardsScore > BLACKJACK_SCORE) return BUST
            return NONE
        }
    }
}
