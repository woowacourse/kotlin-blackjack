package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck
import blackjack.domain.model.result.GameResult

class Dealer(
    name: String,
    cards: List<Card>,
) : Participant(name, cards) {
    constructor(cards: List<Card>) : this(DEFAULT_NAME, cards)

    constructor(vararg cards: Card) : this(DEFAULT_NAME, cards.toList())

    constructor(name: String, vararg cards: Card) : this(name, cards.toList())

    override fun canHit(): Boolean {
        return (computePoint() <= HIT_THRESHOLD)
    }

    override fun openInitialHand(): List<Card> {
        return openHand().take(INITIAL_OPEN_SIZE)
    }

    override fun compareAgainst(other: Participant): GameResult {
        if (other.isBusted()) return GameResult.WIN
        if (!isBlackJack() && other.isBlackJack()) return GameResult.BLACKJACK_LOSE
        return super.compareAgainst(other)
    }

    tailrec fun processHits(
        deck: Deck,
        output: (Dealer, Int) -> Unit,
    ) {
        if (!canHit()) return
        output(this, HIT_THRESHOLD)
        accept(deck.draw())
        processHits(deck, output)
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val HIT_THRESHOLD = 16
        private const val INITIAL_OPEN_SIZE = 1
    }
}
