package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.domain.result.GameResult

class Blackjack(private val deck: CardDeck) {
    fun start(
        players: List<Player>,
        onStartFirstDrawn: (Participants) -> Unit,
        onFirstDrawn: (Participant) -> Unit,
        onDrawnMore: (Participant) -> Unit,
        onEndGame: (List<GameResult>) -> Unit,
    ) {
        val finishedParticipants = Participants(players + Dealer())
            .drawFirst(deck, onStartFirstDrawn, onFirstDrawn)
            .takeTurns(deck, onDrawnMore)

        onEndGame(finishedParticipants.getGameResult())
    }
}
