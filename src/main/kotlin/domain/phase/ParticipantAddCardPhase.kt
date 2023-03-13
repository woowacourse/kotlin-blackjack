package domain.phase

import domain.Participants
import domain.card.CardDeck

class ParticipantAddCardPhase(
    private val playersSelectAddPhase: PlayersSelectAddPhase,
    private val dealerAddPhase: DealerAddPhase
) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        playersSelectAddPhase.runPhase(participants, deck)
        dealerAddPhase.runPhase(participants, deck)
    }
}
