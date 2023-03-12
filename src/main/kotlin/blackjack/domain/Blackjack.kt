package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.result.GameResult

class Blackjack(private val deck: CardDeck, private val players: List<Participant>) {
    fun start(
        onStartDrawn: (Participants) -> Unit,
        onFirstDrawn: (Participant) -> Unit,
        onDrawnMore: (Participant) -> Unit,
        onEndGame: (List<GameResult>) -> Unit,
    ) {
        val participants = Participants(players + Dealer())
        onStartDrawn(participants)

        onEndGame(
            participants
                .drawFirst(deck, onFirstDrawn)
                .takeTurns(deck, onDrawnMore)
                .getGameResult()
        )
    }
}
