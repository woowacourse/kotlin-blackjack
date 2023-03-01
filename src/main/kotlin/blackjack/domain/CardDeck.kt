package blackjack.domain

class CardDeck(cards: List<Card>) {
    private val cards: MutableList<Card> = cards.toMutableList()

    init {
        require(cards.toSet().size == cards.size) { ERROR_EXIST_DUPLICATE_CARDS }
        require(cards.size == CARDS_SIZE) { ERROR_INVALID_CARDS_SIZE }
    }

    val size: Int
        get() = cards.size

    fun draw(): Card = cards.removeFirst()

    companion object {
        private const val CARDS_SIZE = 52
        private const val ERROR_INVALID_CARDS_SIZE = "카드덱 초기 사이즈는 52장이어야 합니다."
        private const val ERROR_EXIST_DUPLICATE_CARDS = "카드덱에는 중복이 없어야 합니다."
    }
}
