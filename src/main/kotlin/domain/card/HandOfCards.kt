package domain.card

class HandOfCards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getTotalCardSum(sumStrategy: SumStrategy): Int {
        return sumStrategy.getSum(this)
    }

    fun countAce() = cards.count { it.number == CardNumber.ACE }

    fun getExceptAceSum() = cards
        .filter { it.number != CardNumber.ACE }
        .sumOf { it.number.value }

    fun showFirstCard(): List<Card> {
        return cards.subList(0, 1)
    }
}
