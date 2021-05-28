package controller

import domain.BlackJackGame
import domain.player.Player
import view.InputView
import view.OutputView

class GameController {

    private val blackJackGame = BlackJackGame(InputView.inputGamblers())

    fun play() {
        giveInitialCards()

        giveGamblerAdditionalCards()
        giveDealerAdditionalCards()

        printResult()
    }

    private fun giveInitialCards() {
        val players = blackJackGame.giveInitialCards()
        OutputView.printPlayersCards(players)
    }

    private fun giveGamblerAdditionalCards() {
        blackJackGame.gamblers.forEach {
            askDrawMore(it)
        }
    }

    private fun askDrawMore(player: Player) {
        while (InputView.askDrawMore(player).isYes()) {
            val player = blackJackGame.givePlayerCard(player)
            OutputView.printPlayerCard(player)
        }
    }

    private fun giveDealerAdditionalCards() {
        val dealer = blackJackGame.giveDealerAdditionalCards()
        OutputView.printPlayerCard(dealer)
    }

    private fun printResult() {
        OutputView.printResult(blackJackGame.gameResult())
    }
}