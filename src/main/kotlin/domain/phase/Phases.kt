package domain.phase

import domain.Participants
import domain.card.CardDeck

class Phases(private val phases: List<Phase>) {
    constructor(vararg phase: Phase) : this(phase.map { it })

    fun run(participants: Participants, deck: CardDeck) {
        phases.forEach { gamePhase ->
            gamePhase.runPhase(participants, deck)
        }
    }
}
