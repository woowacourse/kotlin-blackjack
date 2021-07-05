package controller

import service.GameService
import view.InputView
import view.OutputView

class GameController {

    private val playerInfos = InputView.inputPlayerInfos()
    private val gameService = GameService(playerInfos)

    fun play() {
        initPlayerCards()
        distributeAdditionalCards()
        printResult()
    }

    private fun initPlayerCards() {
        OutputView.printPlayersCards(gameService.distributeInitialCards())
    }

    private fun distributeAdditionalCards() {
        playerInfos.forEach { askPlayerDraw(it.name) }
        OutputView.printPlayerCard(gameService.giveDealerAdditionalCards())
    }

    private fun askPlayerDraw(name: String) {
        while (InputView.askDrawMore(name).isYes()) {
            OutputView.printPlayerCard(gameService.distributeCard(name))
        }
    }

    private fun printResult() {
        OutputView.printResult(gameService.calculateResult())
    }
}
