package blackjack.domain

class BlackJackGame(
    private val player: List<Player>,
    private val dealer: Dealer,
) {
    private val deck = Deck()

    fun initializedHandOutCards(initializedCardCount: Int) {
        repeat(initializedCardCount) {
            player.map { it.drawCard(deck.pop()) }
            dealer.drawCard(deck.pop())
        }
    }

    private fun processPlayerTurn(
        player: Player,
        action: (String) -> UserChoice,
    ) {
        while (!player.isBust()) {
            val choice = action(player.name)
            when (choice) {
                UserChoice.HIT -> player.addCard(deck.pop())
                UserChoice.STAY -> break
            }
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
        const val BUST_STANDARD = 21
    }
}
