package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame(
    val players: List<Player>,
    private val deck: Deck,
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    val dealer = Dealer()

    fun setUp() {
        dealer.setAllCard(deck)
        setInitialPlayerCards(players)
    }

    fun eachPlayerHitOrNot() {
        players.forEach { player ->
            handlePlayerHit(player)
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

    private fun handlePlayerHit(player: Player) {
        while (player.canHit()) {
            val result = inputView.askPlayerHit(player.name)
            if (result) {
                player.addCard(deck.draw())
                outputView.printPlayerCards(player)
            } else {
                break
            }
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
