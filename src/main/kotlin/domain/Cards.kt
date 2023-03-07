package domain

class Cards(list: List<Card>) {
    private val _cards = list.toMutableList()
    val list: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    init {
        check(list.size >= MINIMUM_CARDS_SIZE) { ERROR_CARDS_SIZE }
    }

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
        private const val MINIMUM_CARDS_SIZE = 2
        private const val ERROR_CARDS_SIZE = "[ERROR] 초기 카드는 ${MINIMUM_CARDS_SIZE}장 이상이어야 합니다."
        private const val HAS_ACE = 1
    }
}
