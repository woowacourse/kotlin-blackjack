package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.CardPack
import blackjack.domain.GameResult
import blackjack.domain.PlayerGameResult
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val blackJackGame: BlackJackGame = BlackJackGame(CardPack())
) {
    fun run() {
        runCatching {
            startBlackJackGame()
            onBlackJackGame()
            endBlackJackGame(blackJackGame.judgeGameResults())
        }.onFailure { exception ->
            OutputView.printErrorMessage(exception)
        }
    }

    private fun startBlackJackGame() {
        blackJackGame.entryPlayers(InputView.requestPlayersName())
        OutputView.printCardDividingMessage(blackJackGame.showParticipants())
    }

    private fun onBlackJackGame() {
        // TODO: 이거만 고치면 댐
        blackJackGame.drawAdditionalCards()
        OutputView.printIsDealerReceivedCard(blackJackGame.drawAdditionalCardForDealer())
    }

    private fun endBlackJackGame(gameResults: Pair<List<PlayerGameResult>, List<GameResult>>) {
        OutputView.printFinalCards(blackJackGame.showParticipants())
        OutputView.printGameResults(playerGameResults = gameResults.first, dealerGameResult = gameResults.second)
        OutputView.printBetResults(blackJackGame.judgeBetResults(playersGameResult = gameResults.first))
    }
}
