package domain

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

    private fun isContainAce(): Boolean {
        return _cards.count { it.isAce } == HAS_ACE
    }

    fun getScore(): Score = Score(sum(), isContainAce())

    companion object {
        private const val HAS_ACE = 1
    }
}
