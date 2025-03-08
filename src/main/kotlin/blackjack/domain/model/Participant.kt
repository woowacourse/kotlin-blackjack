package blackjack.domain.model

abstract class Participant(
    val name: String,
) {
    private val cards = mutableListOf<Card>()

    fun showCards(): List<Card> = cards.toList()

    fun drawCard(deck: Deck) {
        cards += deck.pop()
    }

    abstract fun isDrawable(): Boolean
}
