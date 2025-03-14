package blackjack.controller

import blackjack.domain.BetAmount
import blackjack.domain.BlackJackGame
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.utils.retryWhenException
import blackjack.view.BlackJackInputView
import blackjack.view.BlackJackOutputView

class BlackJackController(
    private val inputView: BlackJackInputView,
    private val outputView: BlackJackOutputView,
) {
    fun play() {
        val players = initializePlayers()
        val dealer = Dealer()
        val game = BlackJackGame(dealer, players)

        dealCards(game)
        playPlayersTurns(game)
        playDealerTurns(game)
        showGameResult(game)
    }

    private fun initializePlayers(): List<Player> =
        retryWhenException {
            val names = readPlayerNames()
            names.map { name ->
                val betAmount = readPlayerBetAmount(name)
                Player(name, betAmount)
            }
        }

    private fun readPlayerNames(): List<String> =
        retryWhenException {
            inputView.getNames()
        }

    private fun readPlayerBetAmount(name: String): BetAmount =
        retryWhenException {
            BetAmount(inputView.getBetAmount(name))
        }

    private fun dealCards(game: BlackJackGame) {
        game.dealCards()
        outputView.printInitialDrawMessage(game.dealer, game.players)
    }

    private fun playPlayersTurns(game: BlackJackGame) {
        game.playPlayersTurns(
            getIsHit = { retryWhenException { inputView.getIsHit(it) } },
            printDrawStatus = { outputView.printPlayerDrawStatus(it) },
        )
    }

    private fun playDealerTurns(game: BlackJackGame) {
        game.playDealerTurns(
            printDealerDrawMessage = { outputView.printDealerDrawNotice() },
        )
    }

    private fun showGameResult(game: BlackJackGame) {
        outputView.printPersonResult(game.dealer)
        game.players.forEach { player -> outputView.printPersonResult(player) }

        val gameResult = game.gameResult()
        outputView.printGameResult(gameResult)
    }

    private fun <T> retryWhenException(action: () -> T) =
        retryWhenException(
            action = action,
            onFailure = { e -> outputView.printMessage(e.message) },
        )
}
