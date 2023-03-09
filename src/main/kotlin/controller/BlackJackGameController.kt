package controller

import view.InputView
import view.ResultView

class BlackJackGameController(private val inputView: InputView, private val resultView: ResultView) : Runnable {
    override fun run() {
        val blackJackGame = BlackJackGame(inputView::readNames, inputView::readBettingMoney)
        resultView.printGameInit(blackJackGame.participants.players)
        resultView.printInitCards(blackJackGame.participants)
        blackJackGame.playersSelectAddPhase(inputView::readChoiceOfAddCard, resultView::printPlayerCard)
        blackJackGame.dealerSelectPhase(resultView::printDealerAddCard)
        resultView.printScore(blackJackGame.participants)
        val gameResult = blackJackGame.getGameResult()
        resultView.printParticipantsProfit(gameResult.getDealerProfit(), gameResult.getPlayersProfit())
    }
}
