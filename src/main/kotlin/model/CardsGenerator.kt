package model

class CardsGenerator {
    fun generateCards(): Cards {
        val cards: MutableList<Card> = mutableListOf()
        Shape.entries.forEach { shape ->
            addCardsByRank(cards, shape)
        }
        cards.shuffle()
        return Cards(cards)
    }

    private fun addCardsByRank(
        cards: MutableList<Card>,
        cardShape: Shape,
    ) {
        CardRank.entries.forEach { cardRank ->
            cards.add(Card(cardRank, cardShape))
        }
    }
}
