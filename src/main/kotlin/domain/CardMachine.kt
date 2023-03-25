package domain

import domain.card.Card
import domain.card.CardNumber
import domain.card.Shape

class CardMachine {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        createDeckOfCard()
        cards.shuffle()
    }

    fun getCards(count: Int): List<Card> = List(count) { cards.removeFirst() }

    private fun createDeckOfCard() = Shape.values().forEach { shape -> matchCardValueAndShape(shape) }

    private fun matchCardValueAndShape(shape: Shape) {
        CardNumber.values().map { value ->
            cards.add(Card.of(shape, value))
        }
    }
}
