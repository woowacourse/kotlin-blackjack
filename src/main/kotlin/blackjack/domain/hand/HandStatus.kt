package blackjack.domain.hand

enum class HandStatus {
    BLACKJACK,
    BUST,
    STAND,
    ;

    companion object {
        fun from(hand: Hand): HandStatus {
            return when {
                hand.isBlackJack() -> BLACKJACK
                hand.isBust() -> BUST
                else -> STAND
            }
        }
    }
}
