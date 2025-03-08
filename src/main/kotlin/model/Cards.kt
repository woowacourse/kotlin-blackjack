package model

class Cards(allCards: List<Card>) {
    val allCards: MutableList<Card> = allCards.toMutableList()

    init {
        require(allCards.toSet().size == totalCount()) { DUPLICATE_CARD_ERROR_MESSAGE }
    }

    fun initialCards(): Cards {
        val initialCards = allCards.take(2)
        allCards.removeAll(initialCards)
        return Cards(initialCards)
    }

    fun totalCount() = allCards.size

    fun names() = allCards.map { it.cardName }

    fun scores() = allCards.map { card -> card.cardScore }

    fun aceCount() = allCards.count { card -> card.isAceCard() }

    companion object {
        private const val DUPLICATE_CARD_ERROR_MESSAGE = "[ERROR] 카드는 중복될 수 없습니다"
    }
}
