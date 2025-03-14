package blackjack.model

enum class WinningResult {
    BLACKJACK,
    WIN,
    PUSH,
    LOSE,
    ;

    companion object {
        fun getResult(
            one: Participant,
            other: Participant,
        ): WinningResult {
            val otherBlackjack = other.getHandSize() == 2 && other.getScore() == 21
            val oneBlackjack = one.getHandSize() == 2 && one.getScore() == 21
            return when {
                otherBlackjack && oneBlackjack -> PUSH
                oneBlackjack -> BLACKJACK
                otherBlackjack -> LOSE
                one.isBusted() -> LOSE
                other.isBusted() -> WIN
                other.getScore() > one.getScore() -> LOSE
                other.getScore() < one.getScore() -> WIN
                else -> PUSH
            }
        }

        fun getPrize(compareHand: WinningResult): Double =
            when (compareHand) {
                BLACKJACK -> 1.5
                WIN -> 1.0
                PUSH -> 0.0
                LOSE -> -1.0
            }
    }
}
