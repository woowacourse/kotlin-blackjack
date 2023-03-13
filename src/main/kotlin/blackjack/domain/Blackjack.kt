package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.listener.BlackjackEventListener
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants

class Blackjack(
    private val deck: CardDeck,
    private val players: List<Participant>,
    private val blackjackEventListener: BlackjackEventListener,
) {
    fun start() {
        val participants = Participants(players + Dealer())

        with(blackjackEventListener) {
            onStartDrawn(participants)
            onEndGame(
                participants
                    .drawFirst(deck, ::onFirstDrawn)
                    .takeTurns(deck, ::onDrawnMore)
                    .getGameResult()
            )
        }
    }
}
