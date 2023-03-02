package blackjack.domain

class CardBunch private constructor(cards: MutableSet<Card>) {
    private val _cards: MutableSet<Card> = cards
    val cards: Set<Card> get() = _cards.toSet()

    constructor(vararg cards: Card) : this(cards.toMutableSet()) {
        validateSize()
    }

    private fun validateSize() {
        require(_cards.size == 2) { INITIAL_SIZE_ERROR }
    }

    fun addCard(card: Card) {
        val originSize = _cards.size
        _cards.add(card)
        require(originSize != _cards.size) { DUPLICATE_ERROR }
    }

    companion object {
        private const val INITIAL_SIZE_ERROR = "초기 카드는 2장이 배정되어야 합니다."
        private const val DUPLICATE_ERROR = "중복된 카드는 추가할 수 없습니다."
    }
}
