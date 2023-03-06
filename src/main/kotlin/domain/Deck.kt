package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue

class Deck(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    fun getOneCard() = _cards.removeFirst()

    fun getCards(count: Int): List<Card> {
        return List(count) { getOneCard() }
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

        fun create(count: Int): Deck = Deck(List(count) { CARDS }.flatten().shuffled())
    }
}
