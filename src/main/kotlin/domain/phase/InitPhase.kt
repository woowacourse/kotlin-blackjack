package domain.phase

import domain.CardDeck
import domain.Participant
import domain.Participants
import domain.Players

class InitPhase(val printGameInit: (Players) -> Unit, val printInitCards: (List<Participant>) -> Unit) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        printGameInit(participants.players)
        printInitCards(participants.all)
    }
}
