package domain.phase

import domain.CardDeck
import domain.Dealer
import domain.Participant
import domain.Participants
import domain.Players

class GameScorePrintPhase(
    private val printScore: (List<Participant>) -> Unit,
    private val printGameResult: (Players, Dealer) -> Unit
) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        printScore(participants.all)
        printGameResult(participants.players, participants.dealer)
    }
}
