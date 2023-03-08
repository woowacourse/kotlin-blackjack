package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGameController(
    private val blackJackGame: BlackJackGame = BlackJackGame(),
) {

    fun run() {
        initPlayers()
        drawAdditionalCards()
        checkResult()
    }

    private fun initPlayers() {
        val playersName = InputView.requestPlayersName()
        val battingMoneys = InputView.requestPlayersBattingMoney(playersName)

        blackJackGame.initPlayers(playersName, battingMoneys)
        OutputView.printCardDividingMessage(blackJackGame.dealer, blackJackGame.players)
    }

    private fun drawAdditionalCards() {
        blackJackGame.drawAdditionalCardsForPlayers(
            isPlayerWantedAdditionalCards = InputView::requestAdditionalDraw,
            checkCurrentCards = OutputView::printPlayerCurrentCards
        )
        OutputView.printIsDealerReceivedCard(blackJackGame.drawAdditionalCardsForDealer())
    }

    private fun checkResult() {
        OutputView.printFinalCards(blackJackGame.dealer, blackJackGame.players)
        OutputView.printGameResults(blackJackGame.judgeGameResults())
    }
}
