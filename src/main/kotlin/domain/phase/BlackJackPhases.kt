package domain.phase

import domain.Participants
import domain.card.CardDeck
import domain.result.BetProfitResult

class BlackJackPhases(private val initDrawPhase: InitDrawPhase, private val participantAddPhase: ParticipantAddCardPhase) {

    fun run(participants: Participants, deck: CardDeck): BetProfitResult {
        initDrawPhase.runPhase(participants, deck)
        participantAddPhase.runPhase(participants, deck)
        return BetProfitResult(participants.players, participants.dealer)
    }
}
