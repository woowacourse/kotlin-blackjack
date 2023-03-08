package domain

abstract class Participant(val name: Name, val cards: Cards) {
    abstract fun isPossibleDrawCard(): Boolean
    fun addCard(card: Card) = cards.add(card)
    fun resultSum(): Int = cards.resultSum
    fun isBurst(): Boolean = cards.isBurst
    fun isBlackJack(): Boolean = cards.isBlackJack
}
