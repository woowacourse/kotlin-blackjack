package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.CARD_COUNT_OF_DEALER_MUST_INITIAL_OPEN
import blackjack.domain.card.TrumpCard

class Dealer : Participant() {
    override fun isDrawable(): Boolean {
        return totalScore() <= DEALER_MUST_REACH_SCORE
    }

    override fun getInitialCards(): Set<TrumpCard> {
        return cards.items.take(CARD_COUNT_OF_DEALER_MUST_INITIAL_OPEN).toSet()
    }
}
