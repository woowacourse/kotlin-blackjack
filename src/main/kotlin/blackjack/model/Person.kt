package blackjack.model

abstract class Person {
    private val _cards: MutableList<Card> = mutableListOf()
    open val cards get() = _cards.toList()

    fun addCard(card: Card) = _cards.add(card)

    fun addCards(cards: List<Card>) = _cards.addAll(cards)

    fun calculateTotalScore() = cards.sumOf { card -> card.number.score }

    abstract fun isBust(): Boolean
}
