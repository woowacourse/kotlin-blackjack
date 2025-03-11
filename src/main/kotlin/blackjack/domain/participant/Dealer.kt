package blackjack.domain.participant

import blackjack.domain.card.TrumpCard

class Dealer : Participant() {
    fun isOverMaxScore(): Boolean {
        if (cards.hasAce() && !isBust()) {
            return sumOfCards() + ACE_EXTRACT_SCORE > DEALER_MUST_REACH_SCORE
        }
        return sumOfCards() > DEALER_MUST_REACH_SCORE
    }

    fun first(): TrumpCard = cards.items.first()
}
