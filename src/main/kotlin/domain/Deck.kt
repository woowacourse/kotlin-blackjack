package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue

class Deck(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    fun getNewCard() = _cards.removeFirst()

    fun getCardPairs(count: Int): List<List<Card>> {
        return List(count) { getCardPair() }
    }

    fun getCardPair(): List<Card> {
        val pickedCard = _cards.take(2)
        _cards.removeAll(pickedCard)
        return pickedCard
    }

    companion object {
        private val CARDS = makeCards()

        private fun makeCards(): List<Card> {
            return CardShape.values().flatMap { shape ->
                mapCardValue(shape)
            }
        }

        private fun mapCardValue(shape: CardShape): List<Card> =
            CardValue.values().map { value -> Card(shape, value) }

        fun create(count: Int): Deck = Deck(List(count) { CARDS }.flatten())
    }
}
