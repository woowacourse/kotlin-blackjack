package domain

import model.CardDeck
import model.Participant
import model.Participants

class CardGame(private val cardDeck: CardDeck, private val participants: Participants) {
    fun readyToStart() {
        participants.forEach {
            it.drawFirst(cardDeck)
        }
    }

    fun drawCard(participant: Participant, status: (Participant) -> Unit, needToDraw: (String) -> Boolean) {
        while (participant.isHit(needToDraw)) {
            participant.cards.add(cardDeck.drawCard())
            status(participant)
        }
    }
}
