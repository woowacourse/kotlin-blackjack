package blackjack.domain

class BlackjackGame(
    private val deck: Deck,
    private val participants: Participants,
) {
    fun distributeInitialCards() {
        repeat(INITIAL_CARD_COUNT) {
            participants.drawCard(deck)
        }
    }

    fun playPlayersTurn(
        onResponse: (Player) -> Boolean,
        onDone: (Player) -> Unit,
    ) {
        participants.players.forEach {
            it.playGame(deck, onResponse, onDone)
        }
    }

    fun playDealerTurn() {
        participants.dealer.playGame(deck)
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2
    }
}
