package model

class Cards(val allCards: MutableList<Card>) {
    fun drawCards(count: Int): List<Card> {
        val drawnCards = allCards.take(count)
        allCards.removeAll(drawnCards)
        return drawnCards
    }
}
