package blackjack.domain

class CardDeck {
    private val cards = Card.getAllCards().shuffled().toMutableList()

    fun drawCard(): Card {
        return cards.removeFirstOrNull() ?: throw IllegalArgumentException()
    }
}
