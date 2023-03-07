package domain

import model.CardDeck
import model.Participant
import model.Participants
import model.Players

class CardGame(private val cardDeck: CardDeck, private val participants: Participants) {
    fun readyToStart() {
        participants.forEach {
            it.drawFirst(cardDeck)
        }
    }

    fun drawPlayersCard(players: Players, needToDraw: (String) -> Boolean, status: (Participant) -> Unit) {
        players.forEach {
            drawPlayerCard(it, needToDraw, status)
        }
    }

    private fun drawPlayerCard(participant: Participant, needToDraw: (String) -> Boolean, status: (Participant) -> Unit) {
        while (participant.isPossibleDrawCard() && needToDraw(participant.name.value)) {
            participant.cards.add(cardDeck.drawCard())
            status(participant)
        }
    }

    fun drawDealerCard(participant: Participant, status: () -> Unit) {
        while (participant.isPossibleDrawCard()) {
            status()
            participant.cards.add(cardDeck.drawCard())
        }
    }
}
