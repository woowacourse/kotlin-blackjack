package blackjack.model

abstract class Participant {
    abstract fun hit(card: Card)

    abstract fun initialSetHand(deck: Deck)

    abstract fun hitIfConditionTrue(
        deck: Deck,
        condition: () -> Boolean,
        view: () -> Unit,
    )

    companion object {
        const val INITIAL_HAND_COUNT = 2
    }
}
