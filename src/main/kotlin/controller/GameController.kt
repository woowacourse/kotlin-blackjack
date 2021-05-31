package controller

import service.GameService
import view.InputView
import view.OutputView

class GameController {

    private val playerInfos = InputView.inputPlayerInfos()
    private val gameService = GameService(playerInfos)

    fun play() {
        giveInitialCards()

        giveGamblerAdditionalCards()
        giveDealerAdditionalCards()

        printResult()
    }

    private fun giveInitialCards() {
        OutputView.printPlayersCards(gameService.distributeInitialCards())
    }

    private fun giveGamblerAdditionalCards() {
        playerInfos.forEach {
            askPlayerDraw(it.name)
        }
    }

    private fun askPlayerDraw(name: String) {
        while (InputView.askDrawMore(name).isYes()) {
            OutputView.printPlayerCard(gameService.distributeCard(name))
        }
    }

    private fun giveDealerAdditionalCards() {
        OutputView.printPlayerCard(gameService.giveDealerAdditionalCards())
    }

    private fun printResult() {
        OutputView.printResult(gameService.calculateResult())
    }
}