package blackjack.domain

class BlackJackGame(
    val players: List<Player>,
    private val deck: Deck,
) {
    val dealer = Dealer()

    fun setUp() {
        dealer.setAllCard(deck)
        setInitialPlayerCards(players)
    }

    fun eachPlayerHitOrNot(
        onInput: (Player) -> Boolean,
        onPrint: (Player) -> Unit,
    ) {
        players.forEach { player ->
            handlePlayerHit(player, onInput, onPrint)
        }
    }

    fun hasDealerAdditionalCard(): Boolean {
        return dealer.hasAdditionalCard()
    }

    private fun setInitialPlayerCards(players: List<Player>) {
        players.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.addCard(deck.draw())
            }
        }
    }

    private fun handlePlayerHit(
        player: Player,
        onInput: (Player) -> Boolean,
        onPrint: (Player) -> Unit,
    ) {
        while (player.canHit()) {
            val result = onInput(player)
            if (result) {
                player.addCard(deck.draw())
                onPrint(player)
            } else {
                break
            }
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
