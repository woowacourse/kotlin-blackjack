package blackjack.model

class Dealer(hand: Hand) {
    private val _hand: MutableList<Card> = hand.cards.toMutableList()
    val hand: Hand get() = Hand(_hand.toList())

    fun canHit(): Boolean {
        if (hand.isBust()) return false
        return hand.sumOptimized() < HIT_CONDITION
    }

    fun hit(card: Card) {
        _hand.add(card)
    }

    fun hitUntilBust(deck: Deck) {
        while (canHit()) {
            hit(deck.pull())
        }
    }

    companion object {
        const val HIT_CONDITION = 17
    }
}
