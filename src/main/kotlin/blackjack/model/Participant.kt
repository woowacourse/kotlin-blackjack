package blackjack.model

abstract class Participant(val hand: Hand) {
    fun hit(card: Card) {
        hand.add(card)
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
}
