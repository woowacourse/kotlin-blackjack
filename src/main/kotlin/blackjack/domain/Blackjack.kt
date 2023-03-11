package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.domain.result.BlackjackResult

class Blackjack(private val deck: CardDeck) {
    fun start(
        players: List<Player>,
        onStartFirstDrawn: (Participants) -> Unit,
        onFirstDrawn: (Participant) -> Unit,
        onDrawnMore: (Participant) -> Unit,
        onEndGame: (BlackjackResult) -> Unit,
    ) {
        val finishedParticipants = Participants(players + Dealer())
            .drawFirst(deck, onStartFirstDrawn, onFirstDrawn)
            .takeTurns(deck, onDrawnMore)

        onEndGame(BlackjackResult(finishedParticipants.getCardResults(), finishedParticipants.getMatchResults()))
    }
}
