package blackjack.domain.participant

import blackjack.domain.ParticipantCards

class Dealer(
    cards: ParticipantCards,
) : Participant(cards) {
    fun isOverMaxScore(): Boolean {
        if (cards.hasAce() && !isBustByMaxAce()) {
            return cards.sumOfCards + ACE_EXTRACT_SCORE > DEALER_MAX_SCORE
        }
        return cards.sumOfCards > DEALER_MAX_SCORE
    }

    companion object {
        private const val DEALER_MAX_SCORE = 16
    }
}
