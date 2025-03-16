package blackjack.domain.participant

import blackjack.domain.ParticipantCards
import blackjack.domain.card.TrumpCard

class Dealer(
    cards: ParticipantCards,
) : Participant(cards) {
    override fun showInitialCards(): List<TrumpCard> = takeCards(DEALER_INITIAL_CARD_COUNT)

    override fun isDrawable(): Boolean {
        if (cards.hasAce() && !cards.isBust(ACE_SOFT_SCORE)) {
            return cards.sumOfCards + ACE_SOFT_SCORE <= DEALER_MAX_SCORE
        }
        return cards.sumOfCards <= DEALER_MAX_SCORE
    }

    companion object {
        private const val DEALER_MAX_SCORE = 16
        private const val DEALER_INITIAL_CARD_COUNT = 1
        private const val ACE_SOFT_SCORE = 10
    }
}
