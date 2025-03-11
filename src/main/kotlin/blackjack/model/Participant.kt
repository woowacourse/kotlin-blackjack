package blackjack.model

abstract class Participant(
    val name: String,
    private val hand: Hand,
) {
    val cards: List<Card> get() = hand.cards

    abstract fun showInitialCards(): List<Card>

    abstract fun isDrawable(): Boolean

    fun recieveCards(getCards: (Int) -> List<Card>) {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        addAll(getCards(count))
    }

    fun score(): Int = hand.score()

    fun addAll(cards: List<Card>) {
        hand.addAll(cards)
    }

    fun isBust(): Boolean = hand.isBust()

    companion object {
        const val INITIAL_DRAW_COUNT = 2
        const val DEFAULT_DRAW_COUNT = 1
    }
}
