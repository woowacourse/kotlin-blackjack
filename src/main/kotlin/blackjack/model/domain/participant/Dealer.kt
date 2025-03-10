package blackjack.model.domain.participant

import blackjack.model.domain.card.Hand
import blackjack.model.service.Blackjack.Companion.THRESHOLD

class Dealer(override val name: String = DEALER_NAME) : Participants() {
    override val hand: Hand = Hand(mutableListOf())
    override var status: ParticipantStatus = ParticipantStatus.None

    fun canHit(): Boolean {
        return sumCardNumber <= THRESHOLD
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
    }
}
