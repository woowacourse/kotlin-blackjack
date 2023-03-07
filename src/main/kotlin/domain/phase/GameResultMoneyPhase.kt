package domain.phase

import domain.CardDeck
import domain.Dealer
import domain.Participants
import domain.Players

class GameResultMoneyPhase(
    private val printGameResult: (Players, Dealer) -> Unit
) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        printGameResult(participants.players, participants.dealer)
    }
}
