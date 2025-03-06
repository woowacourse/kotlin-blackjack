package blackjack.controller

import blackjack.domain.model.Cards
import blackjack.domain.model.Choice
import blackjack.domain.model.Dealer
import blackjack.domain.model.Player
import blackjack.domain.model.Verdict
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(private val inputView: InputView = InputView(), private val outputView: OutputView = OutputView()) {
    fun run() {
        val deck = Cards()
        val dealer = Dealer()
        val players: List<Player> = inputView.readPlayerNames().map(::Player)
        val participants: List<Player> = listOf(dealer) + players
        initialDeal(deck, participants)
        processDealerHits(deck, dealer)
        announceResult(dealer, players)
    }

    private fun initialDeal(
        deck: Cards,
        participants: List<Player>,
    ) {
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

    private fun playHand(
        player: Player,
        deck: Cards,
    ) {
        while (true) {
            if (player.isBust()) return
            val choice = Choice(inputView.readPlayerAction(player))
            if (!choice.isHit()) return
            player.accept(deck.draw(1))
            outputView.printPlayerStatus(player)
        }
    }

    private fun processDealerHits(
        deck: Cards,
        dealer: Dealer,
    ) {
        while (dealer.getScore() <= Dealer.DEALER_DRAW_THRESHOLD) {
            outputView.printDealerHitsState()
            dealer.accept(deck.draw(1))
        }
    }

    private fun announceResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        (listOf(dealer) + players).forEach { player ->
            outputView.printPlayerResult(player)
        }

        outputView.printResultsHeader()
        val verdicts = dealer.getDealerVerdicts(players)
        outputView.printDealerVerdicts(dealer, verdicts)
        players.forEach { player ->
            outputView.printPlayerVerdict(player, Verdict.determine(dealer, player))
        }
    }
}
