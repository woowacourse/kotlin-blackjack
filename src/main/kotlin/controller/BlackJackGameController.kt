package controller

import domain.game.BlackJackGame
import view.InputView
import view.ResultView

class BlackJackGameController(private val inputView: InputView, private val resultView: ResultView) : Runnable {
    override fun run() {
        val blackJackGame = BlackJackGame(inputView::readNames, inputView::readBettingMoney)
        blackJackGame.introduceParticipants(resultView::printGameInit, resultView::printInitCards)
        val gameResult = blackJackGame.runGame(
            inputView::readChoiceOfAddCard,
            resultView::printPlayerCard,
            resultView::printDealerAddCard,
        )
        resultView.printScore(blackJackGame.participants)
        resultView.printParticipantsProfit(gameResult.getDealerProfit(), gameResult.getPlayersProfit())
    }
}
