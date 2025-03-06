package blackjack.controller

import blackjack.domain.model.Cards
import blackjack.domain.model.Choice
import blackjack.domain.model.Dealer
import blackjack.domain.model.Dealer.Companion.DEALER_DRAW_THRESHOLD
import blackjack.domain.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(private val inputView: InputView = InputView(), private val outputView: OutputView = OutputView()) {
    fun initialize() {
        val dealer = Dealer()
        val participants: List<Player> = listOf(dealer) + inputView.readPlayerNames().map(::Player)
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
        processDealerHits(deck, dealer)
    }

    private fun playHand(
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

    private fun processDealerHits(
        deck: Cards,
        dealer: Dealer,
    ) {
        while (dealer.getScore() <= DEALER_DRAW_THRESHOLD) {
            outputView.printDealerHitsState()
            dealer.accept(deck.draw(1))
        }
    }
}
