package blackjack.domain.model

class Player {
    private val cards = mutableListOf<Card>()

    fun showCards(): List<Card> = cards.toList()

    init {
        repeat(2) { cards += Deck.giveCard() }
    }
}
