package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.result.BlackjackResult

class Blackjack(private val deck: CardDeck, private val participants: Participants) {
    constructor(deck: CardDeck, players: List<Participant>) : this(deck, Participants(listOf(Dealer()) + players))

    fun start(
        onStartFirstDrawn: (Participants) -> Unit,
        onFirstDrawn: (Participant) -> Unit,
        onDrawnMore: (Participant) -> Unit,
        onEndGame: (BlackjackResult) -> Unit,
    ) {
        participants.drawFirst(deck, onStartFirstDrawn, onFirstDrawn)
        participants.takeTurns(deck, onDrawnMore)
        onEndGame(BlackjackResult(participants.getCardResults(), participants.getMatchResults()))
    }
}
