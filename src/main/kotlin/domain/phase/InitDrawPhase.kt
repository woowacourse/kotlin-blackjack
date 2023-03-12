package domain.phase

import domain.Participants
import domain.Players
import domain.card.CardDeck

class InitDrawPhase(val printGameInit: (Players) -> Unit, val printInitCards: (Participants) -> Unit) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        participants.all.forEach { participant ->
            repeat(INIT_DRAW_CARD_SIZE) { participant.addCard(deck.draw()) }
        }
        printGameInit(participants.players)
        printInitCards(participants)
    }

    companion object {
        private const val INIT_DRAW_CARD_SIZE = 2
    }
}
