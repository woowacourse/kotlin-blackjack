package blackjack.domain

abstract class Participant {
    protected abstract val onBusted: () -> Unit
    protected val hand: Hand = Hand()
    val cards: List<Card> get() = hand.cards
    val score: Score get() = hand.getScore(onBusted)

    fun draw(card: Card) {
        hand.draw(card)
    }
}
