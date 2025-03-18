package model.participant

import model.card.Card
import model.card.CardName
import model.card.Cards
import model.card.HandCards

abstract class Participant(private val cards: Cards) {
    val handCards
        get() = HandCards(cards)

    val score: Int
        get() = handCards.score

    val isBust: Boolean
        get() = handCards.isBust

    val isBlackJack: Boolean = handCards.isBlackJack

    val cardNames: List<CardName>
        get() = handCards.cardNames

    abstract fun turn(drawnCard: Card): Boolean

    abstract fun canHit(): Boolean

    protected fun addCard(card: Card) {
        handCards.addCard(card)
    }
}
