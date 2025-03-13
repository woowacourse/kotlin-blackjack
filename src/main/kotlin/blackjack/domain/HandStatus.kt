package blackjack.domain

enum class HandStatus {
    BLACKJACK,
    BUST,
    PLAYING,
    ;

    companion object {
        fun from(hand: Hand): HandStatus {
            return when {
                hand.isBlackJack() -> BLACKJACK
                hand.isBust() -> BUST
                else -> PLAYING
            }
        }
    }
}
