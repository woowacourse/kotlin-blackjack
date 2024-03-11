package blackjack.model

class Hand(cards: List<Card> = emptyList()) {
    private var _cards: List<Card> = cards
    val cards: List<Card>
        get() = _cards

    operator fun plus(other: Card): Hand {
        _cards += other
        return Hand(cards)
    }

    fun calculate(): Int {
        var total = cards.sumOf { it.number.value }
        val aceCount = cards.count { it.number == CardNumber.ACE }

        repeat(aceCount) {
            if (total <= BLACKJACK_NUMBER) return@repeat
            total -= DIFF_ACE_TO_ONE
        }
        return total
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
        const val DIFF_ACE_TO_ONE = 10
    }
}
