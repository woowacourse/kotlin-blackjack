package domain

abstract class Player(
    val name: String,
    private val _cards: MutableList<Card>,
) {

    val cards: List<Card> get() = _cards.toList()

    fun validPlayerSum(): Int {
        if ((calculateCardValueSum() < 10) and (countAce() != 0)) {
            return calculateCardValueSum() + 10
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
}
