package blackjack.model

abstract class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()

    fun addCard(card: Card) = _cards.add(card)

    abstract fun isBust(): Boolean
}
