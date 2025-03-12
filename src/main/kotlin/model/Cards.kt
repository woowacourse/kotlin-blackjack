package model

class Cards(allCards: List<Card>) {
    val allCards: MutableList<Card> = allCards.toMutableList()
    val totalCount: Int
        get() = allCards.size

    val names: List<Pair<String, String>>
        get() = allCards.map { it.cardName }

    val scores: List<Int>
        get() = allCards.map { card -> card.cardScore }

    val aceCount: Int
        get() = allCards.count { card -> card.isAceCard }

    init {
        require(allCards.toSet().size == totalCount) { DUPLICATE_CARD_ERROR_MESSAGE }
    }

    fun initialCards(): Cards {
        val initialCards = allCards.take(INITIAL_CARD_COUNT)
        allCards.removeAll(initialCards)
        return Cards(initialCards)
    }

    fun drawCard(): Card {
        val drawnCard = allCards.take(DRAW_DEFAULT_COUNT)
        allCards.removeAll(drawnCard)
        return drawnCard.first()
    }

    companion object {
        private const val DUPLICATE_CARD_ERROR_MESSAGE = "[ERROR] 카드는 중복될 수 없습니다"
        private const val DRAW_DEFAULT_COUNT = 1
        private const val INITIAL_CARD_COUNT = 2
    }
}
