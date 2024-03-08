package blackjack.model

class Dealer(hand: Hand) {
    private val _handCards: MutableList<Card> = hand.cards.toMutableList()
    val hand: Hand get() = Hand(_handCards.toList())

    fun canHit(): Boolean {
        if (hand.isBust()) return false
        return hand.sumOptimized() < HIT_CONDITION
    }

    fun hit(card: Card) {
        if (canHit()) {
            _handCards.add(card)
        }
    }

    companion object {
        const val HIT_CONDITION = 17
    }
}
