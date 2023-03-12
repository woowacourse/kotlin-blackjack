package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGameController(
    private val blackJackGame: BlackJackGame = BlackJackGame(),
) {

    fun run() {
        runCatching {
            initPlayers()
            drawAdditionalCards()
            checkResult()
        }.onFailure { exception ->
            OutputView.printErrorMessage(exception)
        }
    }

    private fun initPlayers() {
        val names = InputView.requestPlayersName()

        blackJackGame.initPlayers(names, InputView::requestBattingMoney)
        OutputView.printCardDividingMessage(blackJackGame.dealer, blackJackGame.blackJackPlayers)
    }

    private fun drawAdditionalCards() {
        blackJackGame.drawAdditionalCardsForPlayers(
            isPlayerWantedAdditionalCards = InputView::requestAdditionalDraw,
            checkCurrentCards = OutputView::printPlayerCurrentCards
        )
        blackJackGame.drawAdditionalCardsForDealer(OutputView::printIsDealerReceivedCard)
    }

    private fun checkResult() {
        OutputView.printFinalCards(blackJackGame.dealer, blackJackGame.blackJackPlayers)
        OutputView.printGameResults(blackJackGame.judgeResult())
    }
}
