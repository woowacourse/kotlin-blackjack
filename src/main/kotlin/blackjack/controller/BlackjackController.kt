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
    }

    private fun dealCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        repeat(2) {
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
}
