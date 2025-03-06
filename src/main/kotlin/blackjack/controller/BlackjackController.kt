package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.enums.Action
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play() {
        val playerNames = inputView.readPlayerNames()
        val dealer = Dealer()
        val players = playerNames.map { Player(it) }
        dealCards(dealer, players)
        outputView.printDealingResult(dealer, players)
        players.forEach { dealMoreCard(it) }
        dealMoreCard(dealer)
    }

    private fun dealCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        repeat(INITIAL_CARD_COUNT) {
            dealer.addCard(Deck.pick())
            players.forEach { it.addCard(Deck.pick()) }
        }
    }

    private fun dealMoreCard(player: Player) {
        val action = inputView.readHitOrStay(player)
        if (action == Action.HIT) {
            player.addCard(Deck.pick())
            outputView.printPlayerCards(player)
            dealMoreCard(player)
        }
    }

    private fun dealMoreCard(dealer: Dealer) {
        val dealerScore = dealer.calculateScore()
        if (dealerScore <= DEALER_HIT_CONDITION) {
            dealer.addCard(Deck.pick())
            outputView.printDealerHit()
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
        private const val DEALER_HIT_CONDITION = 16
    }
}
