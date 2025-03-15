package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck

class Dealer(
    name: String,
    cards: List<Card>,
) : Participant(name, cards) {
    private var isInitialOpen = true

    constructor(cards: List<Card>) : this(DEFAULT_NAME, cards)

    constructor(vararg cards: Card) : this(DEFAULT_NAME, cards.toList())

    constructor(name: String, vararg cards: Card) : this(name, cards.toList())

    override fun canHit(): Boolean {
        return (computePoint() <= HIT_THRESHOLD)
    }

    override fun showHand(): List<Card> {
        if (isInitialOpen) return super.showHand().take(INITIAL_OPEN_SIZE)
        return super.showHand()
    }

    fun processHits(
        deck: Deck,
        output: (Dealer, Int) -> Unit,
    ) {
        isInitialOpen = false
        while (canHit()) {
            output(this, HIT_THRESHOLD)
            accept(deck.draw())
        }
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val HIT_THRESHOLD = 16
        private const val INITIAL_OPEN_SIZE = 1
    }
}
