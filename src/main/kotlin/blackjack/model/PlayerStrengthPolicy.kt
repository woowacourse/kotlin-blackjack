package blackjack.model

class PlayerStrengthPolicy : StrengthPolicy {
    override fun strength(cards: Cards): Int =
        when {
            cards.isBlackJack() -> StrengthPolicy.BLACKJACK_STRENGTH
            cards.isBusted() -> BUSTED_STRENGTH
            else -> cards.sum()
        }

    companion object {
        private const val BUSTED_STRENGTH = -1
    }
}
