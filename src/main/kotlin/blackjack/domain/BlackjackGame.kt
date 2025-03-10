package blackjack.domain

class BlackjackGame(
    private val deck: Deck,
) {
    fun distributeInitialCards(
        dealer: Dealer,
        players: Players,
    ) {
        repeat(INITIAL_CARD_COUNT) {
            dealer.drawCard(deck.pick())
            players.drawCard(deck)
        }
    }

    fun playPlayersTurn(
        players: Players,
        onResponse: (Player) -> Boolean,
        onDone: (Player) -> Unit,
    ) {
        players.playGame(deck, onResponse, onDone)
    }

    fun playDealerTurn(dealer: Dealer) {
        dealer.playGame(deck)
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2
    }
}
