package domain.phase

import domain.Participants
import domain.card.CardDeck

interface Phase {
    fun runPhase(participants: Participants, deck: CardDeck)
}
