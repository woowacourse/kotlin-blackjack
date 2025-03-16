package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants

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
        onPlayerChoice: (Participant) -> Boolean,
        onPlayerDraw: (Participant) -> Unit,
        onDealerDraw: (Participant) -> Unit,
    ) {
        participants.playGame(deck::pick, onPlayerChoice, onPlayerDraw, onDealerDraw)
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2
    }
}
