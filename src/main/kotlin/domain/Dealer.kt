package domain

class Dealer(private val _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()

    fun validDealerSum(): Int {
        if ((calculateCardValueSum() < 10) and (countAce() != 0)) {
            return calculateCardValueSum() + 10
        }

        return calculateCardValueSum()
    }

    fun calculateCardValueSum(): Int = _cards.sumOf { Card.valueOf(it) }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun isOverSumCondition(): Boolean = (calculateCardValueSum() > SUM_CONDITION)
    private fun countAce(): Int = _cards.filter { card ->
        card.value == Card.Value.ACE
    }.size

    companion object {
        private const val SUM_CONDITION = 16
        fun create(cards: List<Card>) = Dealer(cards.toMutableList())
    }
}
