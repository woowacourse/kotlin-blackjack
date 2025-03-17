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
        pair.dealer.setInitialCard(deck)
        setInitialPlayerCards(pair.players)
        outputView.showInitialCards(pair)
    }

    fun run() {
        eachPlayerHitOrNot()
        if (pair.dealer.needsAdditionalCard()) {
            outputView.printDealerHaveAdditionalCard()
        }
        addDealerCard()
    }

    fun eachPlayerHitOrNot() {
        pair.players.forEach { player ->
            handlePlayerHit(player)
        }
    }

    private fun addDealerCard() {
        while (pair.dealer.canHit()) {
            pair.dealer.addCard(deck.draw())
        }
    }

    private fun setInitialPlayerCards(players: List<Player>) {
        players.forEach { it.setInitialCard(deck) }
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
