package blackjack.model.domain.participant

import blackjack.model.domain.card.Card

class Player(override val name: String) : Participants() {
    override val cards: MutableList<Card> = mutableListOf()
    override var status: ParticipantStatus = ParticipantStatus.None

    fun compareScores(number: Int) {
        if (status != ParticipantStatus.Bust) {
            status = ParticipantStatus.compare(sumCardNumber, number)
        }
    }
}
