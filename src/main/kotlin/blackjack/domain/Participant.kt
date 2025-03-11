package blackjack.domain

abstract class Participant {
    protected abstract val onBusted: () -> Unit
    protected val hand: Hand = Hand()
    val cards: List<Card> get() = hand.cards
    val score: Int get() = hand.getScore(onBusted)

    fun draw(card: Card) {
        hand.draw(card)
    }
}
