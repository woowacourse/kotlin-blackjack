package model

class Cards(val allCards: MutableList<Card>) : List<Card> by allCards {
    init {
        require(allCards.distinct().size == getCardsCount()) { "[ERROR] 카드는 중복될 수 없습니다" }
    }

    fun drawCards(count: Int): Cards {
        val drawnCards = allCards.take(count)
        allCards.removeAll(drawnCards)
        return Cards(drawnCards.toMutableList())
    }

    fun getCardsCount() = allCards.size

    fun getCardNames() = allCards.map { it.cardName }

    fun getCardScores() = allCards.map { card -> card.cardScore }

    fun aceCount() = allCards.count { card -> card.isAceCard() }

    fun addCards(cards: Cards) {
        cards.forEach { card ->
            allCards.add(card)
        }
    }
}
