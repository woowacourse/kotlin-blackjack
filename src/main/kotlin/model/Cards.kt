package model

class Cards(val allCards: MutableList<Card>) {
    init {
        require(allCards.distinct().size == getCardsCount()) { "[ERROR] 카드는 중복될 수 없습니다" }
    }

    fun drawCards(count: Int): Cards {
        val drawnCards = allCards.take(count)
        allCards.removeAll(drawnCards)
        return Cards(drawnCards.toMutableList())
    }

    fun getCardsCount() = allCards.size

    fun showInitialCards(count: Int): Card = allCards[count - 1]

    fun aceCount() = allCards.count { card -> card.cardRank == CardRank.ACE }
}
