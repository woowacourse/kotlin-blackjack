package blackjack.controller

import InitialParticipants
import blackjack.domain.model.Deck
import blackjack.domain.model.Money
import blackjack.domain.model.betting.BettingPlayer
import blackjack.domain.model.betting.BettingPlayers
import blackjack.domain.model.profit.ProfitParticipants
import blackjack.domain.model.service.InitService
import blackjack.domain.model.service.PlayingService
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val deck = Deck()
        val playerNames = inputView.readPlayerNames()
        val initService = InitService(playerNames, deck)
        val bettingPlayers = initBettingPlayers(playerNames)
        val initialParticipants = initParticipants(initService)
        val playingService = PlayingService(initialParticipants.toPlayingParticipants(), deck)
        playHand(playingService)
        announceResult(playingService.calculateProfitPlayers(bettingPlayers))
    }

    private fun initBettingPlayers(playerNames: Set<String>): BettingPlayers {
        val bettingPlayers =
            playerNames.map { name ->
                val money = retryEvent { Money(inputView.readPlayerBetAmount(name)) }
                BettingPlayer(name, money)
            }
        return BettingPlayers(bettingPlayers)
    }

    private fun initParticipants(initService: InitService): InitialParticipants {
        val initialParticipants = initService.initPlayingParticipants()
        outputView.printInitialDeals(initialParticipants)
        outputView.printParticipantsStatus(initialParticipants)
        return initialParticipants
    }

    private fun playHand(playingService: PlayingService) {
        playingService.playPlayers(
            retryEvent { inputView::readPlayerAction },
            outputView::printPlayerStatus,
            outputView::printPlayerStatus,
        )
        playingService.playDealer {
            outputView.printDealerHitsState()
        }
        outputView.printParticipantsResult(playingService.playingParticipants)
    }

    private fun announceResult(profitParticipants: ProfitParticipants) {
        outputView.printResultsHeader()
        outputView.printDealerProfit(profitParticipants.profitDealer)
        outputView.printPlayersProfit(profitParticipants.profitPlayer)
    }

    private fun <T> retryEvent(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { outputView.printErrorMessage(it.message ?: it.stackTraceToString()) }
        }
    }
}
