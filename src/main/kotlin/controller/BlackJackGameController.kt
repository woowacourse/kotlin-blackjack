package controller

import domain.BlackJackGame
import domain.Name
import domain.Names
import domain.PlayerInfo
import domain.phase.DealerAddPhase
import domain.phase.GameScorePhase
import domain.phase.InitDrawPhase
import domain.phase.Phases
import domain.phase.PlayersSelectAddPhase
import view.Answer
import view.InputView
import view.ResultView

class BlackJackGameController(private val inputView: InputView, private val resultView: ResultView) : Runnable {
    override fun run() {
        val blackJackGame = initGame()
        val result = blackJackGame.runGame(getPlayersNameAndBet())
        resultView.printGameResult(result)
    }

    private fun initGame(): BlackJackGame {
        val initDrawPhase = InitDrawPhase(resultView::printGameInit, resultView::printInitCards)
        val playersSelectAddPhase = PlayersSelectAddPhase(::getChoiceOfAddCard, resultView::printPlayerCard)
        val dealerSelectAddPhase = DealerAddPhase(resultView::printDealerAddCard)
        val gameScorePhase = GameScorePhase(resultView::printScore)
        val phases =
            Phases(initDrawPhase, playersSelectAddPhase, dealerSelectAddPhase, gameScorePhase)
        return BlackJackGame(phases)
    }

    private fun getPlayersNameAndBet(): List<PlayerInfo> {
        val names = getNames()
        return inputView.readPlayersBetMoney(names)
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
}
