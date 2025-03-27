package blackjack.model.card

class Hand(
    val cards: List<Card>,
) {
    constructor(vararg cards: Card) : this(cards.toList())

    val size get() = cards.size

    fun score(): Int {
        val score = cards.sumOf { it.denomination.number }
        if (cards.any { it.isAce() } && score + BONUS_ACE_SCORE <= BLACKJACK_SCORE) {
            return score + BONUS_ACE_SCORE
        }
        return score
    }

    operator fun plus(card: Card): Hand = Hand(this.cards + card)

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val BONUS_ACE_SCORE = 10
    }
}
