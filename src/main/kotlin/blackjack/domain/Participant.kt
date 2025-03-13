package blackjack.domain

abstract class Participant {
    protected abstract val onBusted: () -> Unit
    protected val hand: Hand by lazy { Hand(onBusted) }
    val cards: List<Card> get() = hand.cards
    val score: Score get() = hand.score

    fun draw(card: Card) {
        hand.draw(card)
    }
}
