package model

class Cards(allCards: List<Card>) {
    val allCards: MutableList<Card> = allCards.toMutableList()

    init {
        require(allCards.distinct().size == getCardsCount()) { "[ERROR] 카드는 중복될 수 없습니다" }
    }

    fun getInitialCards(): Cards {
        val initialCards = allCards.take(2)
        allCards.removeAll(initialCards)
        return Cards(initialCards)
    }

    fun getCardsCount() = allCards.size

    fun getCardNames() = allCards.map { it.cardName }

    fun getCardScores() = allCards.map { card -> card.cardScore }

    fun aceCount() = allCards.count { card -> card.isAceCard() }
}
