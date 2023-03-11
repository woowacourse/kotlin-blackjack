package domain

import domain.card.Card

abstract class Player(val name: String, val cards: Cards) {
    fun getScore(): Score = cards.score

    fun isBlackJack(): Boolean = cards.isBlackJack()

    fun addCard(card: Card) = cards.addCard(card)
}
