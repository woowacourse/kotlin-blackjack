package blackjack.controller

import blackjack.domain.model.profit.ProfitParticipants
import blackjack.domain.model.service.BlackJackService
import blackjack.view.BaseGameView
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val playerNames = inputView.readPlayerNames()
        val blackJackService = BlackJackService.from(playerNames.toList(), BaseGameView(inputView, outputView))
        val bettingPlayers = blackJackService.bettingPlayers(playerNames)
        playHand(blackJackService)
        blackJackService.playDealer(outputView::printDealerHitsState)
        announceResult(blackJackService.toProfitPlayers(bettingPlayers))
    }

    private fun playHand(blackJackService: BlackJackService) {
        blackJackService.dealInitialCard()
        blackJackService.playPlayers()

        outputView.printParticipantsResult(blackJackService.playingParticipants)
    }

    private fun announceResult(profitParticipants: ProfitParticipants) {
        outputView.printResultsHeader()
        outputView.printDealerProfit(profitParticipants.dealer)
        outputView.printPlayersProfit(profitParticipants.players)
    }
}
