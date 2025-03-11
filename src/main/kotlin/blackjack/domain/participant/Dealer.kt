package blackjack.domain.participant

import blackjack.domain.ParticipantCards
import blackjack.domain.card.TrumpCard

class Dealer(
    cards: ParticipantCards,
) : Participant(cards) {
    override fun getInitialCards(): List<TrumpCard> = cards.allCards.take(DEALER_INITIAL_CARD_COUNT)

    fun isOverMaxScore(): Boolean {
        if (cards.hasAce() && !isBustByMaxAce()) {
            return cards.sumOfCards + ACE_EXTRACT_SCORE > DEALER_MAX_SCORE
        }
        return cards.sumOfCards > DEALER_MAX_SCORE
    }

    companion object {
        private const val DEALER_MAX_SCORE = 16
        private const val DEALER_INITIAL_CARD_COUNT = 1
    }
}
