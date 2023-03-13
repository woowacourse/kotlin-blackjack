package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.listener.BlackjackEventListener
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.result.GameResult

class Blackjack(
    private val deck: CardDeck,
    private val players: List<Participant>,
    private val eventListener: BlackjackEventListener,
) {
    fun start() {
        val participants = Participants(players + Dealer())
        eventListener.onStartDrawn(participants)

        val result = startGame(participants)
        eventListener.onEndGame(result)
    }

    private fun startGame(participants: Participants): List<GameResult> = participants
        .drawFirst(deck, eventListener::onFirstDrawn)
        .takeTurns(deck, eventListener::onDrawnMore)
        .getGameResult()
}
