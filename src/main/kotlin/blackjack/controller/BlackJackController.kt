package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.CardHand
import blackjack.domain.CardPack
import blackjack.domain.DrawState
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.PlayerGameResult
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val blackJackGame: BlackJackGame = BlackJackGame(CardPack())
) {
    fun run() {
        runCatching {
            startGame()
            inGame()
            endGame(blackJackGame.judgeGameResults())
        }.onFailure { exception ->
            OutputView.printErrorMessage(exception)
        }
    }

    private fun startGame() {
        blackJackGame.entryPlayers(InputView.requestPlayersName(), InputView::requestBetAmount)
        OutputView.printCardDividingMessage(blackJackGame.showParticipants())
    }

    private fun inGame() {
        blackJackGame.showParticipants().second.forEach { player ->
            drawAdditionalCard(player)
        }
        OutputView.printIsDealerReceivedCard(blackJackGame.drawAdditionalCardForDealer())
    }

    private fun drawAdditionalCard(player: Player) {
        while (InputView.requestAdditionalDraw(player) && blackJackGame.drawAdditionalCardForPlayer(player) == DrawState.POSSIBLE) {
            OutputView.printCardResults(player)
        }
        if (player.cardHand.size == CardHand.INITIAL_CARDS_SIZE) {
            OutputView.printCardResults(player)
        }
    }

    private fun endGame(gameResults: Pair<List<PlayerGameResult>, List<GameResult>>) {
        OutputView.printFinalCards(blackJackGame.showParticipants())
        OutputView.printGameResults(playerGameResults = gameResults.first, dealerGameResult = gameResults.second)
        OutputView.printBetResults(blackJackGame.judgeBetResults(playersGameResult = gameResults.first))
    }
}
