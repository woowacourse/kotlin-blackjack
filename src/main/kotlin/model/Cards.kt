package model

class Cards(val allCards: MutableList<Card>) {
    init {
        require(allCards.isNotEmpty()) { "[ERROR] 카드는 1장이상 가져야 학다일 수 없습니다" }
        require(allCards.distinct().size == allCards.size) { "[ERROR] 카드는 중복될 수 없습니다" }
    }

    fun drawCards(count: Int): List<Card> {
        val drawnCards = allCards.take(count)
        allCards.removeAll(drawnCards)
        return drawnCards
    }

    fun aceCount() = allCards.count { card -> card.cardRank == CardRank.ACE }
}
