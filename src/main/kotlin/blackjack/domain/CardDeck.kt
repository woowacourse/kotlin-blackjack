package blackjack.domain

class CardDeck(private val cards: MutableList<Card>) {
    fun drawCard(): Card {
        return cards.removeFirstOrNull() ?: throw IllegalArgumentException()
    }
}
