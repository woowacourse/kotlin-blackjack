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
        onDraw: (Player) -> Unit,
    ) {
        participants.players.forEach { player ->
            player.playGame(
                deck,
                onResponse = onResponse,
                onDraw = onDraw,
            )
        }
    }

    fun playDealerTurn(onDraw: (Dealer) -> Unit) {
        participants.dealer.playGame(
            deck,
            onDraw = onDraw,
        )
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2
    }
}
