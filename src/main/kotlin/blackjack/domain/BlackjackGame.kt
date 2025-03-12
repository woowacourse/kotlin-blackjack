package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

class BlackjackGame(
    private val deck: Deck,
    private val participants: Participants,
) {
    fun distributeInitialCards() {
        repeat(INITIAL_CARD_COUNT) {
            participants.drawCard(deck)
        }
    }

    fun playTurns(
        onPlayerResponse: (Player) -> Boolean,
        onPlayerDraw: (Player) -> Unit,
        onDealerDraw: (Dealer) -> Unit,
    ) {
        participants.playGame(deck, onPlayerResponse, onPlayerDraw, onDealerDraw)
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2
    }
}
