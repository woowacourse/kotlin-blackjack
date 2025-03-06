package blackjack.model

class Cards(
    cards: List<Card>,
) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card> get() = _cards.map { card -> card.copy() }

    var size: Int = this.cards.size
        private set

    fun add(card: Card) {
        _cards.add(card)
    }

    fun calculateScore(): Int {
        val aceCount: Int = cards.count { card -> card.isDenominationAce(card) }
        val score: Int = cards.sumOf { card -> card.denomination.number }
        return when (aceCount) {
            1 -> if (score < 11) score + 11 else score + 1
            2 -> if (score < 10) score + 11 + 1 else score + aceCount * 1
            3 -> if (score < 9) score + 11 + 1 + 1 else score + aceCount * 1
            4 -> if (score < 8) score + 11 + 1 + 1 + 1 else score + aceCount * 1
            else -> score
        }
    }
}
