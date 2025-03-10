package blackjack.model.domain.participant

import blackjack.model.domain.card.Hand
import blackjack.model.domain.participant.ParticipantStatus.Companion.isBust

class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())
    override var status: ParticipantStatus = ParticipantStatus.None

    override fun canHit(): Boolean {
        return isBust(sumCardNumber) == ParticipantStatus.Bust
    }

    fun compareScores(number: Int) {
        if (status != ParticipantStatus.Bust) {
            status = ParticipantStatus.compare(sumCardNumber, number)
        }
    }
}
