package blackjack.domain.model.card

class Hand {
    private var _cards = listOf<Card>()
    private val cards
        get() = _cards.deepCopy()

    fun add(card: Card) {
        _cards += card
    }

    fun toList(): List<Card> {
        return cards
    }

    fun isBust(): Boolean {
        return getScore() > BLACK_JACK_NUMBER
    }

    fun isBlackJack(): Boolean {
        return cards.size == 2 && getScore() == BLACK_JACK_NUMBER
    }

    fun isNotBlackJack(): Boolean {
        return !isBlackJack()
    }

    fun isMoreThan(score: Int): Boolean {
        return getScore() > score
    }

    fun getScore(): Int {
        val cardValues: Int = cards.sumOf { it.cardNumber.value }

        if (cards.any { it.isAce() } && cardValues + ACE_EXTRA_SCORE <= BLACK_JACK_NUMBER) {
            return cardValues + ACE_EXTRA_SCORE
        }
        return cardValues
    }

    companion object {
        fun List<Card>.deepCopy(): List<Card> {
            return map(Card::copy)
        }

        private const val ACE_EXTRA_SCORE = 10
        private const val BLACK_JACK_NUMBER = 21

        fun of(vararg cards: Card): Hand {
            val hand = Hand()
            cards.map { hand.add(it) }
            return hand
        }
    }
}
