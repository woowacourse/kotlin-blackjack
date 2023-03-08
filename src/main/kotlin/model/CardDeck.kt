package model

class CardDeck(value: List<Card>) {
    private val _value: MutableList<Card> = value.toMutableList()

    val size: Int
        get() = _value.size

    init {
        require(value.distinct().size == value.size) { CARD_DECK_DUPLICATE_ERROR }
        require(value.size == CARD_DECK_SIZE) { CARD_DECK_SIZE_ERROR }
    }

    fun drawCard(): Card = _value.removeFirst()

    fun shuffled() = CardDeck(_value.shuffled())

    companion object {
        private const val CARD_DECK_SIZE = 52
        private const val CARD_DECK_DUPLICATE_ERROR = "카드덱은 중복될 수 없습니다"
        private const val CARD_DECK_SIZE_ERROR = "카드덱은 52장이어야 합니다"
        fun createCardDeck(): CardDeck = CardDeck(Rank.values().flatMap { rank -> Suit.values().map { suit -> Card(rank, suit) } })
    }
}
