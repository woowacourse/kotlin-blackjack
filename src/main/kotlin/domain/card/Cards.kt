package domain.card

class Cards(list: List<Card>) {
    private val _cards = list.toMutableList()
    val list: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    fun sum(): Int {
        return list.sumOf { it.cardNumber.value }
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    fun isContainAce(): Boolean {
        return _cards.any { it.isAce }
    }

    fun getScore(): Score = Score(this)
}
