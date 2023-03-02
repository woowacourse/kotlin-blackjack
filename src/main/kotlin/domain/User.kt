package domain

class User(
    val name: String,
    private val _cards: MutableList<Card>,
) {

    val cards: List<Card> get() = _cards.toList()
    fun calculateCardValueSum(): Int {
        return _cards.sumOf { Card.valueOf(it) }
    }

    companion object {
        fun create(userNameAndCard: Pair<String, List<Card>>): User =
            User(userNameAndCard.first, userNameAndCard.second.toMutableList())
    }
}
