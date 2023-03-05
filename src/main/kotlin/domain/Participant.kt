package domain

abstract class Participant(val name: Name, protected val cards: Cards) {
    abstract fun showInitCards(): List<Card>
    abstract fun isPossibleDrawCard(): Boolean
    fun showAllCards(): List<Card> = cards.cards
    fun addCard(card: Card) = cards.add(card)
    fun resultSum(): Int = cards.resultSum
    fun isBurst(): Boolean = cards.isBurst
}
