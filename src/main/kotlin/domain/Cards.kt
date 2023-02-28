package domain

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        check(cards.size >= MINIMUM_CARDS_SIZE) { ERROR_CARDS_SIZE }
    }

    fun sum(): Int {
        return cards.sumOf { it.cardNumber.value }
    }

    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
        private const val ERROR_CARDS_SIZE = "[ERROR] 초기 카드는 ${MINIMUM_CARDS_SIZE}장 이상이어야 합니다."
    }
}
