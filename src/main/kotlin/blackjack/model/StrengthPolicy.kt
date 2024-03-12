package blackjack.model

interface StrengthPolicy {
    fun strength(cards: Cards): Int

    companion object {
        const val BLACKJACK_STRENGTH = 22
    }
}
