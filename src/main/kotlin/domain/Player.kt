package domain

abstract class Player(
    val name: String,
    private val _cards: MutableList<Card>,
) {

    val cards: List<Card> get() = _cards.toList()

    fun validPlayerSum(): Int {
        if ((calculateCardValueSum() < ACE_CARD_PLUS_TEN) and (countAce() != NO_ACE)) {
            return calculateCardValueSum() + ACE_CARD_PLUS_TEN
        }

        return calculateCardValueSum()
    }

    fun calculateCardValueSum(): Int = _cards.sumOf { Card.valueOf(it) }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    private fun countAce(): Int = _cards.filter { card ->
        card.value == Card.Value.ACE
    }.size

    companion object {
        private const val ACE_CARD_PLUS_TEN = 10
        private const val NO_ACE = 0
    }
}
