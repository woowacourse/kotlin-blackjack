package domain

class User(
    val name: String,
    private val _cards: MutableList<Card>,
) {

    val cards: List<Card> get() = _cards.toList()

    fun validUserSum(): Int {
        if ((calculateCardValueSum() < 10) and (countAce() != 0)) {
            return calculateCardValueSum() + 10
        }

        return calculateCardValueSum()
    }

    fun calculateCardValueSum(): Int {
        return _cards.sumOf { Card.valueOf(it) }
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    private fun countAce(): Int = _cards.filter { card ->
        card.value == Card.Value.ACE
    }.size

    companion object {
        fun create(userNameAndCard: Pair<String, List<Card>>): User =
            User(userNameAndCard.first, userNameAndCard.second.toMutableList())
    }
}
