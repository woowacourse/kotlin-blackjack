package domain

import domain.card.Card
import domain.card.Cards

abstract class Participant(val name: Name, val cards: Cards) {
    abstract fun isPossibleDrawCard(): Boolean
    fun addCard(card: Card) = cards.add(card)
    fun resultSum(): Int = cards.resultSum
    fun isBurst(): Boolean = cards.isBust
    fun isBlackJack(): Boolean = cards.isBlackJack
}
