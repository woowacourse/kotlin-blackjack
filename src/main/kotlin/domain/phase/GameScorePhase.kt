package domain.phase

import domain.Participant
import domain.Participants
import domain.card.CardDeck

class GameScorePhase(
    private val printScore: (List<Participant>) -> Unit
) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        printScore(participants.all)
    }
}
