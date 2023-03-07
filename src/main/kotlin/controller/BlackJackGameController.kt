package controller

import domain.Answer
import domain.Player
import view.InputView
import view.ResultView

class BlackJackGameController(private val inputView: InputView, private val resultView: ResultView) : Runnable {
    override fun run() {
        val blackJackGame = initGame()
        mainGame(blackJackGame)
        gameResult(blackJackGame)
    }

    private fun initGame(): BlackJackGame {
        val names = inputView.readNames()
        val blackJackGame = BlackJackGame(names)
        resultView.printGameInit(blackJackGame.participants.players)
        resultView.printInitCards(blackJackGame.participants)
        return blackJackGame
    }

    private fun mainGame(blackJackGame: BlackJackGame) {
        blackJackGame.playersSelectAddPhase(::getChoiceOfAddCard, resultView::printPlayerCard)
        blackJackGame.dealerSelectPhase(resultView::printDealerAddCard)
    }

    private fun gameResult(blackJackGame: BlackJackGame) {
        resultView.printScore(blackJackGame.participants)
        resultView.printGameResult(blackJackGame.getGameResult())
    }

    private fun getChoiceOfAddCard(player: Player): Boolean {
        val choice = inputView.readChoiceOfAddCard(player.name)
        return choice == Answer.YES
    }
}
