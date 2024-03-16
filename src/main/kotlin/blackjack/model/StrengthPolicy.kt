package blackjack.model

abstract class StrengthPolicy {
    abstract val bustedStrength: Int
    fun strength(cards: Cards): Int =
        when {
            cards.isBlackJack() -> BLACKJACK_STRENGTH
            cards.isBusted() -> bustedStrength
            else -> cards.sum()
        }

    companion object {
        const val BLACKJACK_STRENGTH = 22
    }
}
