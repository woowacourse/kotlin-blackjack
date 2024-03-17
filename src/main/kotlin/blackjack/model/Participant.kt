package blackjack.model

abstract class Participant(
    val hand: Hand,
    profit: Amount,
) {
    var profit = profit
        protected set

    fun hit(card: Card) {
        hand.add(card)
    }

    fun setInitialHand(deck: Deck) {
        repeat(INITIAL_HAND_COUNT) {
            hit(deck.pull())
        }
    }

    abstract fun hitWhileConditionTrue(
        deck: Deck,
        condition: () -> Boolean = { false },
        view: () -> Unit,
    )

    companion object {
        const val INITIAL_HAND_COUNT = 2
        const val INITIAL_PROFIT = 0
    }
}
