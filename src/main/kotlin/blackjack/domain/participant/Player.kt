package blackjack.domain.participant

import blackjack.domain.ParticipantCards
import blackjack.domain.ParticipantCards.Companion.BUST_STANDARD
import blackjack.domain.card.TrumpCard

class Player(
    val name: String,
    cards: ParticipantCards,
) : Participant(cards) {
    override fun showInitialCards(): List<TrumpCard> = takeCards(PLAYER_INITIAL_CARD_COUNT)

    override fun isDrawable(): Boolean = cards.sumOfCards <= BUST_STANDARD

    companion object {
        private const val PLAYER_INITIAL_CARD_COUNT = 2
    }
}
