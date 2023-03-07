package domain.phase

import domain.CardDeck
import domain.Participants

class Phases(private val phases: List<Phase>) {
    constructor(vararg phase: Phase) : this(phase.map { it })

    fun run(participants: Participants, deck: CardDeck) {
        phases.forEach { gamePhase ->
            gamePhase.runPhase(participants, deck)
        }
    }
}
