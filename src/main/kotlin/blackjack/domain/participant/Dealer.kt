package blackjack.domain.participant

import blackjack.domain.card.TrumpCard

class Dealer : Participant() {
    override fun isDrawable(): Boolean {
        return totalScore() <= DEALER_MUST_REACH_SCORE
    }

    fun first(): TrumpCard = cards.items.first()
}
