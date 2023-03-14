package blackjack.domain.card

class CardDeck(cards: List<Card>) {
    private val cards: MutableList<Card> = cards.toMutableList()

    val size: Int
        get() = cards.size

    init {
        require(cards.toSet().size == cards.size) { ERROR_EXIST_DUPLICATE_CARDS }
        require(cards.size == CARDS_SIZE) { ERROR_INVALID_CARDS_SIZE }
    }

    fun drawCard(): Card {
        require(cards.isNotEmpty()) { ERROR_EMPTY_CARDS }
        return cards.removeFirst()
    }

    fun putCard(card: Card) {
        cards.add(0, card)
    }

    companion object {
        private const val CARDS_SIZE = 52
        private const val ERROR_INVALID_CARDS_SIZE = "카드덱 초기 사이즈는 ${CARDS_SIZE}장이어야 합니다."
        private const val ERROR_EXIST_DUPLICATE_CARDS = "카드덱에는 중복이 없어야 합니다."
        private const val ERROR_EMPTY_CARDS = "카드덱에 카드가 없습니다."
    }
}
