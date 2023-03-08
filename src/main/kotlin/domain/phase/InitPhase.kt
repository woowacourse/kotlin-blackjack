package domain.phase

import domain.CardDeck
import domain.Participants
import domain.Players

class InitPhase(val printGameInit: (Players) -> Unit, val printInitCards: (Participants) -> Unit) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        printGameInit(participants.players)
        printInitCards(participants)
    }
}
