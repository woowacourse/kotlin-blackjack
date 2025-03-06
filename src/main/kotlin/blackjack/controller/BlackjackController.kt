package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val cardDeck = CardDeck()
        val players = inputView.getPlayers().map { name -> Player(name, cardDeck) }

        players.forEach { player ->
            getIsPlayerDrawMore(player)
        }
    }

    private fun getIsPlayerDrawMore(player: Player) {
        while (!player.hand.isBusted()) {
            if (inputView.getIsDrawMore()) player.draw() else break
        }
    }
}
