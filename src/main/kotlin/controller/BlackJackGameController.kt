package controller

import domain.BlackJackGameResult
import domain.Name
import domain.Names
import domain.phase.DealerAddPhase
import domain.phase.InitPrintPhase
import domain.phase.Phases
import domain.phase.PlayersSelectAddPhase
import view.Answer
import view.InputView
import view.ResultView

class BlackJackGameController(private val inputView: InputView, private val resultView: ResultView) : Runnable {
    override fun run() {
        val blackJackGame = initGame()
        val result = blackJackGame.runGame(getNames())
        printGameResult(result)
    }

    private fun initGame(): BlackJackGame {
        val initPrintPhase = InitPrintPhase(resultView::printGameInit, resultView::printInitCards)
        val playersSelectAddPhase = PlayersSelectAddPhase(::getChoiceOfAddCard, resultView::printPlayerCard)
        val dealerSelectAddPhase = DealerAddPhase(resultView::printDealerAddCard)
        val phases = Phases(initPrintPhase, playersSelectAddPhase, dealerSelectAddPhase)
        return BlackJackGame(phases)
    }

    private fun getNames(): Names {
        return inputView.readNames() ?: getNames()
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

    private fun printGameResult(result: BlackJackGameResult) {
        resultView.printScore(result.participants.all)
        resultView.printGameResult(result.participants.players, result.participants.dealer)
    }
}
