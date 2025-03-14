package blackjack.domain.model

class Dealer(name: String, cards: List<Card>) : Participant(name, cards) {
    constructor(cards: List<Card>) : this(DEFAULT_NAME, cards)
    constructor(vararg cards: Card) : this(DEFAULT_NAME, cards.toList())
    constructor(name: String, vararg cards: Card) : this(name, cards.toList())

    override fun canHit(): Boolean {
        return (computePoint() <= HIT_THRESHOLD)
    }

    fun showStartingHand(): List<Card> {
        return hand.show().take(1)
    }

    override fun showHand(): List<Card> {
        return hand.show()
    }

    fun processHits(
        deck: Deck,
        printStatus: (Dealer) -> Unit,
    ) {
        while (canHit()) {
            printStatus(this)
            accept(deck.draw())
        }
    }

    companion object {
        const val HIT_THRESHOLD = 16
        private const val DEFAULT_NAME = "딜러"
    }
}
