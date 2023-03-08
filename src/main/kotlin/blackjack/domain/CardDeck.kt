package blackjack.domain

class CardDeck {
    private val cards = Card.all().shuffled().toMutableList()

    fun drawCard(): Card {
        return cards.removeFirstOrNull() ?: throw IllegalArgumentException()
    }
}
