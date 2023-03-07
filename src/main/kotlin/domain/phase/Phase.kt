package domain.phase

import domain.CardDeck
import domain.Participants

interface Phase {
    fun runPhase(participants: Participants, deck: CardDeck)
}
