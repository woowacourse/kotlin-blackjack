package domain.phase

import domain.Participants
import domain.Players
import domain.card.CardDeck

class InitPhase(val printGameInit: (Players) -> Unit, val printInitCards: (Participants) -> Unit) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        printGameInit(participants.players)
        printInitCards(participants)
    }
}
