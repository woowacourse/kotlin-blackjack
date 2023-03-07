package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGameController(
    private val blackJackGame: BlackJackGame = BlackJackGame(),
) {
    fun start(){
        val playersName = InputView.requestPlayersName()
        val battingMoneys = InputView.requestPlayersBattingMoney(playersName)

        blackJackGame.initPlayers(
           playerNames = playersName,
            battingMoneys = battingMoneys
        )
    }

    fun run() {
        val playerNames = InputView.requestPlayersName()
        val battingMoneys = InputView.requestPlayersBattingMoney(playerNames)

        blackJackGame.initPlayers(
            playerNames,
            battingMoneys,
        )

        OutputView.printCardDividingMessage(blackJackGame.dealer, blackJackGame.players)

        blackJackGame.drawAdditionalCardsForPlayers(
            isPlayerWantedAdditionalCards = InputView::requestAdditionalDraw,
            checkCurrentCards = OutputView::printPlayerCurrentCards
        )

        OutputView.printIsDealerReceivedCard(blackJackGame.drawAdditionalCardsForDealer())

        OutputView.printFinalCards(blackJackGame.dealer, blackJackGame.players)

        OutputView.printGameResults(blackJackGame.judgeGameResults())
    }
}
