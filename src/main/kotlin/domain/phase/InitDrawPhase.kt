package domain.phase

import domain.Participants
import domain.Players
import domain.card.CardDeck

class InitDrawPhase(val printGameInit: (Players) -> Unit, val printInitCards: (Participants) -> Unit) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        participants.all.forEach { participant ->
            repeat(2) { participant.addCard(deck.draw()) }
        }
        printGameInit(participants.players)
        printInitCards(participants)
    }
}
