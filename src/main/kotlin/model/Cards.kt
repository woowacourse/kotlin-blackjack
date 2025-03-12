package model

class Cards(allCards: List<Card>) {
    private val _allCards: MutableList<Card> = allCards.toMutableList()
    val allCards: List<Card> get() = _allCards.toList()

    init {
        require(allCards.distinct().size == _allCards.size) { DUPLICATE_CARD_ERROR_MESSAGE }
    }

    fun drawCards(count: Int): List<Card> {
        if (_allCards.isEmpty()) {
            _allCards.addAll(CardsGenerator().generateCards().allCards)
        }

        val drawnCards = _allCards.take(count)
        _allCards.removeAll(drawnCards.toSet())
        return drawnCards
    }

    companion object {
        private const val DUPLICATE_CARD_ERROR_MESSAGE = "[ERROR] 카드는 중복될 수 없습니다"
    }
}
