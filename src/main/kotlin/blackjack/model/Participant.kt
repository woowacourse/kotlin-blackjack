package blackjack.model

abstract class Participant {
    private val _cards: MutableList<Card> = mutableListOf()
    open val cards get() = _cards.toList()

    fun addCard(card: Card) = _cards.add(card)

    fun calculateTotalScore() = cards.sumOf { card -> card.number.score }

    abstract fun isBust(): Boolean
}
