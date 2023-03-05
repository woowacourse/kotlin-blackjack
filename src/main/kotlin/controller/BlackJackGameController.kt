package controller

import domain.Name
import domain.Names
import view.Answer
import view.InputView
import view.ResultView

class BlackJackGameController(private val inputView: InputView, private val resultView: ResultView) : Runnable {
    override fun run() {
        val blackJackGame = initGame()
        mainGame(blackJackGame)
        gameResult(blackJackGame)
    }

    private fun initGame(): BlackJackGame {
        val blackJackGame = BlackJackGame(getNames())
        resultView.printGameInit(blackJackGame.players)
        resultView.printInitCards(blackJackGame.participants)
        return blackJackGame
    }

    private fun getNames(): Names {
        return inputView.readNames() ?: getNames()
    }

    private fun mainGame(blackJackGame: BlackJackGame) {
        blackJackGame.playersSelectAddPhase(::getChoiceOfAddCard, resultView::printPlayerCard)
        blackJackGame.dealerSelectPhase(resultView::printDealerAddCard)
    }

    private fun getChoiceOfAddCard(name: Name): Boolean {
        return when (getAnswerOfAddCard(name)) {
            Answer.YES -> true
            Answer.NO -> false
        }
    }

    private fun getAnswerOfAddCard(name: Name): Answer {
        return inputView.readChoiceOfAddCard(name) ?: getAnswerOfAddCard(name)
    }

    private fun gameResult(blackJackGame: BlackJackGame) {
        resultView.printScore(blackJackGame.participants)
        resultView.printGameResult(blackJackGame.players, blackJackGame.dealer)
    }
}
