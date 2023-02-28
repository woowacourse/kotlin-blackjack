package blackjack.domain

class Player(val name: String) {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun receive(card: Card) {
        _cards.add(card)
    }

    fun getScore(): Int {
        return _cards.sumOf {
            it.value
        }
    }
}
