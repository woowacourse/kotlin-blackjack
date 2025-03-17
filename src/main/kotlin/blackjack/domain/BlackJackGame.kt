package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.participant.BlackJackPair
import blackjack.domain.participant.Player
import blackjack.view.blackjackView.BlackJackInputView
import blackjack.view.blackjackView.BlackJackOutputView

class BlackJackGame(
    private val pair: BlackJackPair,
    private val deck: Deck,
    private val outputView: BlackJackOutputView,
    private val inputView: BlackJackInputView,
) {
    fun setUp() {
        pair.dealer.setAllCard(deck)
        setInitialPlayerCards(pair.players)
        outputView.showInitialCards(pair)
    }

    fun run() {
        eachPlayerHitOrNot()
        if (hasDealerAdditionalCard()) {
            outputView.printDealerHaveAdditionalCard()
        }
    }

    fun eachPlayerHitOrNot() {
        pair.players.forEach { player ->
            handlePlayerHit(player)
        }
    }

    fun hasDealerAdditionalCard(): Boolean {
        return pair.dealer.hasAdditionalCard()
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
