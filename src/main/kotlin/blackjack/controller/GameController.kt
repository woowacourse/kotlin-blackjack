package blackjack.controller

import blackjack.domain.model.Money
import blackjack.domain.model.betting.BettingPlayer
import blackjack.domain.model.betting.BettingPlayers
import blackjack.domain.model.profit.ProfitParticipants
import blackjack.domain.model.service.BlackJackService
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val playerNames = inputView.readPlayerNames()
        val blackJackService = BlackJackService.from(playerNames.toList())
        val bettingPlayers = initBettingPlayers(playerNames)
        playHand(blackJackService)
        announceResult(blackJackService.toProfitPlayers(bettingPlayers))
    }

    private fun initBettingPlayers(playerNames: Set<String>): BettingPlayers {
        val bettingPlayers =
            playerNames.map { name ->
                val money = retryEvent { Money(inputView.readPlayerBetAmount(name)) }
                BettingPlayer(name, money)
            }
        return BettingPlayers(bettingPlayers)
    }

    private fun playHand(blackJackService: BlackJackService) {
        blackJackService.dealInitialCard(outputView::printInitialDeals, outputView::printParticipantsStatus)
        blackJackService.playPlayers(
            retryEvent { inputView::readPlayerAction },
            outputView::printPlayerStatus,
        )

        outputView.printParticipantsResult(blackJackService.playingParticipants)
    }

    private fun announceResult(profitParticipants: ProfitParticipants) {
        outputView.printResultsHeader()
        outputView.printDealerProfit(profitParticipants.dealer)
        outputView.printPlayersProfit(profitParticipants.players)
    }

    private fun <T> retryEvent(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { outputView.printErrorMessage(it.message ?: it.stackTraceToString()) }
        }
    }
}
