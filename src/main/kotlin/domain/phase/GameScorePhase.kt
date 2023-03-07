package domain.phase

import domain.CardDeck
import domain.Participant
import domain.Participants

class GameScorePhase(
    private val printScore: (List<Participant>) -> Unit
) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        printScore(participants.all)
    }
}
