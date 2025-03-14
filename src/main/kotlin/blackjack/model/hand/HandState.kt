package blackjack.model.hand

enum class HandState {
    ALIVE,
    BUST,
    BLACKJACK,
    ;

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val BLACKJACK_CARD_COUNT = 2

        fun from(
            score: Int,
            cardCount: Int,
        ) = when {
            score > BLACKJACK_SCORE -> BUST
            score < BLACKJACK_SCORE -> ALIVE
            cardCount == BLACKJACK_CARD_COUNT -> BLACKJACK
            else -> ALIVE
        }
    }
}
