class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        require(cards.distinct().size == cards.size) { CARD_DUPLICATE_ERROR }
    }
    fun add(card: Card) {
        require(!cards.contains(card)) { CARD_DUPLICATE_ERROR }
        _cards.add(card)
    }

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
    }
}
