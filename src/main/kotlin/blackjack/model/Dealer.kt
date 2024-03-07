package blackjack.model

class Dealer(handCards: HandCards) {
    private val _handCards: MutableList<Card> = handCards.cards.toMutableList()
    val handCards: HandCards get() = HandCards(_handCards.toList())

    fun canHit(): Boolean {
        if (handCards.isBust()) return false
        return handCards.sumOptimized() < HIT_CONDITION
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
