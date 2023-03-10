package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.view.InputView
import blackjack.view.InputView.requestPlayersInput
import blackjack.view.OutputView

// TODO: View의 호출을 최소한으로 줄여보자.
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
        blackJackGame.initPlayers(requestPlayersInput())
        OutputView.printCardDividingMessage(blackJackGame.dealer, blackJackGame.players)
    }

    private fun drawAdditionalCards() {
        blackJackGame.drawAdditionalCardsForPlayers(
            isPlayerWantedAdditionalCards = InputView::requestAdditionalDraw,
            checkCurrentCards = OutputView::printPlayerCurrentCards
        )
        blackJackGame.drawAdditionalCardsForDealer(OutputView::printIsDealerReceivedCard)
    }

    private fun checkResult() {
        OutputView.printFinalCards(blackJackGame.dealer, blackJackGame.players)
        OutputView.printGameResults(blackJackGame.judgeGameResults())
    }
}
