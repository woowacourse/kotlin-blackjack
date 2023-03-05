package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue

class CardMachine {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        addCards()
        cards.shuffle()
    }

    fun getNewCard() = cards.removeFirst()

    fun getCardPairs(count: Int): List<List<Card>> {
        return List(count) { getCardPair() }
    }

    fun getCardPair(): List<Card> {
        val pickedCard = cards.take(2)
        cards.removeAll(pickedCard)
        return pickedCard
    }

    private fun addCards() {
        for (shape in CardShape.values()) {
            addCardValue(shape)
        }
    }

    private fun addCardValue(shape: CardShape) {
        CardValue.values().map { value ->
            cards.add(Card(shape, value))
        }
    }
}
