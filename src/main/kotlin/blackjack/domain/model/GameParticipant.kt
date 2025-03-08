package blackjack.domain.model

abstract class GameParticipant(
    val name: String,
) {
    private val cards = mutableListOf<Card>()

    init {
        repeat(2) { drawCard() }
    }

    fun showCards(): List<Card> = cards.toList()

    fun drawCard() {
        cards += Deck.pop()
    }

    abstract fun play()

    abstract fun isDrawFinish(): Boolean
}
