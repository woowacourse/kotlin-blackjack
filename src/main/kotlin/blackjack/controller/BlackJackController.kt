package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.CardPack
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val blackJackGame: BlackJackGame = BlackJackGame(CardPack())
) {
    fun run() {
        runCatching {
            blackJackGame.entryPlayers(InputView.requestPlayersName())
            OutputView.printCardDividingMessage(blackJackGame.showParticipants())
            blackJackGame.drawAdditionalCards()
            OutputView.printIsDealerReceivedCard(blackJackGame.drawAdditionalCardForDealer())
            OutputView.printFinalCards(blackJackGame.showParticipants())
            val gameResults = blackJackGame.judgeGameResults()
            OutputView.printGameResults(playerGameResults = gameResults.first, dealerGameResult = gameResults.second)
            OutputView.printBetResults(blackJackGame.judgeBetResults(playersGameResult = gameResults.first))
        }.onFailure { exception ->
            OutputView.printErrorMessage(exception)
        }
    }
}
