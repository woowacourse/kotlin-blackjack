package blackjack.controller

import blackjack.domain.model.Cards
import blackjack.domain.model.Choice
import blackjack.domain.model.Dealer
import blackjack.domain.model.Participants
import blackjack.domain.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(private val inputView: InputView = InputView(), private val outputView: OutputView = OutputView()) {
    fun run() {
        val deck = Cards()
        val participants = Participants(listOf(Dealer()) + inputView.readPlayerNames().map(::Player))
        initialDeal(deck, participants)
        printInitialDeal(participants)
        participants.filterPlayers().forEach { player ->
            playHand(player, deck)
        }
        processDealerHits(deck, participants.findDealer())
        announceResult(participants.findDealer(), participants.filterPlayers())
    }

    private fun initialDeal(
        deck: Cards,
        participants: Participants,
    ) {
        participants.players.forEach { player ->
            player.accept(deck.draw(2))
        }
    }

    private fun printInitialDeal(participants: Participants) {
        outputView.printInitialDeals(participants)
        participants.players.forEach { player ->
            outputView.printPlayerStatus(player)
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
        dealer.getPlayerVerdict(players).forEach { (player, verdict) ->
            outputView.printPlayerVerdict(player, verdict)
        }
    }
}
