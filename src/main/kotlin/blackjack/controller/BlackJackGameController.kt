package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.view.InputView
import blackjack.view.InputView.requestAdditionalDraw
import blackjack.view.OutputView

class BlackJackGameController(
    val blackJackGame: BlackJackGame = BlackJackGame(),
) {

    fun run() {
        val playerNames = InputView.requestPlayersName()
        val battingMoneys = InputView.requestPlayersBattingMoney(playerNames)

        blackJackGame.initPlayers(
            playerNames,
            battingMoneys,
            OutputView::printPlayerCurrentCards
        )

        OutputView.printCardDividingMessage(blackJackGame.dealer, blackJackGame.players)

        blackJackGame.drawAdditionalCardsForPlayers(::requestAdditionalDraw)

        OutputView.printIsDealerReceivedCard(blackJackGame.drawAdditionalCardsForDealer())

        OutputView.printFinalCards(blackJackGame.dealer, blackJackGame.players)

        OutputView.printGameResults(blackJackGame.judgeGameResults())
    }
}
