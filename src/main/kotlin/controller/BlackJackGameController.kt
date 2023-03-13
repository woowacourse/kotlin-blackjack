package controller

import domain.BlackJackGame
import domain.Name
import domain.Names
import domain.PlayerInfo
import domain.phase.BlackJackPhases
import domain.phase.DealerAddPhase
import domain.phase.InitDrawPhase
import domain.phase.ParticipantAddCardPhase
import domain.phase.PlayersSelectAddPhase
import domain.result.BetProfitResult
import view.Answer
import view.InputView
import view.ResultView

class BlackJackGameController(private val inputView: InputView, private val resultView: ResultView) : Runnable {
    override fun run() {
        val blackJackGame = createBlackJackGame()
        val result = blackJackGame.runGame(getPlayersInfo())
        printResult(result)
    }

    private fun createBlackJackGame(): BlackJackGame {
        val initDrawPhase = InitDrawPhase(resultView::printGameInit, resultView::printInitCards)
        val participantAddCardPhase = ParticipantAddCardPhase(
            PlayersSelectAddPhase(::getChoiceOfAddCard, resultView::printPlayerCard),
            DealerAddPhase(resultView::printDealerAddCard)
        )
        val phases =
            BlackJackPhases(initDrawPhase, participantAddCardPhase)
        return BlackJackGame(phases)
    }

    private fun printResult(result: BetProfitResult) {
        resultView.printScore(result)
        resultView.printGameResult(result)
    }

    private fun getPlayersInfo(): List<PlayerInfo> {
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
