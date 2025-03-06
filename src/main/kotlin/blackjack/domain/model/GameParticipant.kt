package blackjack.domain.model

abstract class GameParticipant(val name: String) {
    private val cards = mutableListOf<Card>()

    init {
        repeat(2) { cards += Deck.giveCard() }
    }

    fun showCards(): List<Card> = cards.toList()

    abstract fun drawCard()
}
