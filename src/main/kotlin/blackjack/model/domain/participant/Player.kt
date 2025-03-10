package blackjack.model.domain.participant

import blackjack.model.domain.card.Hand

class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())
    override var status: ParticipantStatus = ParticipantStatus.None

    fun compareScores(number: Int) {
        if (status != ParticipantStatus.Bust) {
            status = ParticipantStatus.compare(sumCardNumber, number)
        }
    }
}
