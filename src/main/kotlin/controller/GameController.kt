package controller

import service.GameService
import view.InputView
import view.OutputView

class GameController {

    private val playerNames = InputView.inputPlayerNames()
    private val gameService = GameService(InputView.inputPlayers(playerNames))

    fun play() {
        giveInitialCards()

        giveGamblerAdditionalCards(playerNames)
        giveDealerAdditionalCards()

        printResult()
    }

    private fun giveInitialCards() {
        OutputView.printPlayersCards(gameService.distributeInitialCards())
    }

    private fun giveGamblerAdditionalCards(names: List<String>) {
        names.forEach {
            askPlayerDraw(it)
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