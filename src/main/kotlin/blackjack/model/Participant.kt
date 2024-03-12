package blackjack.model

abstract class Participant(val hand: Hand) {
    fun hit(card: Card) {
        hand.add(card)
    }

    fun initialSetHand(deck: Deck) {
        repeat(INITIAL_HAND_COUNT) {
            hit(deck.pull())
        }
    }

    fun hitIfConditionTrue(
        deck: Deck,
        condition: () -> Boolean,
        view: () -> Unit,
    ) {
        while (condition()) {
            hit(deck.pull())
            view()
        }
    }

    companion object {
        private const val INITIAL_HAND_COUNT = 2
    }
}
