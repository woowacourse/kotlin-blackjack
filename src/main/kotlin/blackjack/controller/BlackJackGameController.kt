package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.BlackJackReferee
import blackjack.view.InputView
import blackjack.view.InputView.requestAdditionalDraw
import blackjack.view.OutputView

class BlackJackController(
    val blackJackGame: BlackJackGame = BlackJackGame(),
) {


    fun run() {
        val playerNames = InputView.requestPlayersName()
        val battingMoneys = InputView.requestPlayersBattingMoney(playerNames)

        blackJackGame.initPlayers(
            playerNames,
            battingMoneys
        )

        OutputView.printCardDividingMessage(blackJackGame.dealer, blackJackGame.players)

        blackJackGame.drawAdditionalCardsForPlayers(::requestAdditionalDraw)

        OutputView.printIsDealerReceivedCard(blackJackGame.drawAdditionalCardsForDealer())

        OutputView.printFinalCards(blackJackGame.dealer, blackJackGame.players)

        OutputView.printGameResults(blackJackGame.judgeGameResults())

//        runCatching {
//            showDividingCards()
//            drawAdditionalCards()
//            drawAdditionalCardForDealer()
//            showFinalCards()
//            judgeGameResults()
//        }.onFailure { exception ->
//            OutputView.printErrorMessage(exception)
//        }
    }


//    private fun showDividingCards() = OutputView.printCardDividingMessage(dealer, players)
//
//    private fun showFinalCards() = OutputView.printFinalCards(dealer, players)
//
//    private fun judgeGameResults() {
//        val totalGameResult = blackJackReferee.judgeTotalGameResults(players, dealer)
//
//        OutputView.printGameResults(totalGameResult)
//    }
}
