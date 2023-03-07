package blackjack.domain

class CardPack {
    var cards = Card.ALL_CARDS.toMutableList()

    fun draw(): Card {
        val card = cards.shuffled()[0]
        cards.removeAt(0)

        if (cards.size == 0) {
            cards.addAll(Card.ALL_CARDS.toList())
        }

        return card
    }
}
