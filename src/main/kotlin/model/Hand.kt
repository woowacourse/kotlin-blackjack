package model

class Hand(initialCards: List<Card>) {
    private val _handCards: MutableList<Card> = initialCards.toMutableList()
    val handCards: List<Card> get() = _handCards.toList()

    fun addCard(card: Card) {
        _handCards.add(card)
    }

    fun getScore(): Int {
        return _handCards.sumOf { it.cardRank.score }
    }

    fun getCardsCount(): Int = _handCards.size
}
