package domain

import model.CardDeck
import model.Participant
import model.Participants
import model.ParticipantsProfitResult

class CardGame(private val cardDeck: CardDeck, private val participants: Participants) {
    fun readyToStart() {
        participants.drawFirstCard(cardDeck)
    }

    fun start(status: (Participant) -> Unit): ParticipantsProfitResult {
        participants.players.forEach {
            drawToStart(it, status)
        }
        drawToStart(participants.dealer, status)

        return participants.getParticipantsProfitResult()
    }

    private fun drawToStart(participant: Participant, status: (Participant) -> Unit) {
        if (participant.isHit()) {
            participant.drawCard(cardDeck)
            status(participant)
            drawToStart(participant, status)
        }
    }
}
