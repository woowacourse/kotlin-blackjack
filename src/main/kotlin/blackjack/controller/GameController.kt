package blackjack.controller

import blackjack.domain.model.Cards
import blackjack.domain.model.Choice
import blackjack.domain.model.Dealer
import blackjack.domain.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(private val inputView: InputView = InputView(), private val outputView: OutputView = OutputView()) {
    fun initialize() {
        val participants: List<Player> = listOf(Dealer()) + inputView.readPlayerNames().map(::Player)
        val deck = Cards()
        participants.forEach { player ->
            player.accept(deck.draw(2))
        }
        participants.forEach { player ->
            outputView.printPlayerStatus(player)
        }
        participants.filterNot { it is Dealer }.forEach { player ->
            playHand(player, deck)
        }
    }

    fun playHand(
        player: Player,
        deck: Cards,
    ) {
        while (true) {
            outputView.requestPlayerAction(player)
            val choice = Choice(inputView.readPlayerAction())
            if (!choice.isHit() || player.isBust()) return
            player.accept(deck.draw(1))
            outputView.printPlayerStatus(player)
        }
    }
}
