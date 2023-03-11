package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.domain.result.BlackjackResult

class Blackjack(private val deck: CardDeck, private val participants: Participants) {
    constructor(deck: CardDeck, players: List<Player>) : this(deck, Participants(listOf(Dealer()) + players))

    fun readyToStart() {
        participants.drawFirst(deck)
    }

    fun start(onDrawn: (Participant) -> Unit): BlackjackResult {
        participants.takePlayerTurns(deck, onDrawn)
        participants.takeDealerTurns(deck, onDrawn)

        return BlackjackResult(
            participants.getCardResults(),
            participants.getMatchResults(),
        )
    }

    fun getFirstOpenCards(): Map<String, List<Card>> = participants.getFirstOpenCards()
}
