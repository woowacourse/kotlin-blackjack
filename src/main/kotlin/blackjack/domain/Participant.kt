package blackjack.domain

abstract class Participant(
    private val hand: Hand,
) {
    abstract val onBusted: () -> Unit

    val cards: List<Card> = hand.value

    val score: Int
        get() =
            hand.score ?: run {
                onBusted()
                SCORE_BUSTED
            }

    fun draw(card: Card) {
        hand.draw(card)
    }

    companion object {
        const val SCORE_BUSTED = -1
    }
}
