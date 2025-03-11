package blackjack.domain

abstract class Participant {
    protected abstract val onBusted: () -> Unit
    protected val hand: Hand = Hand()
    val cards: List<Card> get() = hand.cards
    val handState: HandState get() = hand.getHandState(onBusted)
    val score: Int get() = handState.score

    fun draw(card: Card) {
        hand.draw(card)
    }
}
