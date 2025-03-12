package blackjack.controller

import blackjack.domain.BetAmount
import blackjack.domain.BlackJackGame
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
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

    private fun initializePlayers(): List<Player> {
        val names = readPlayerNames()
        return names.map { name ->
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
        outputView.printFirstDrawMessage(game.players)
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
            printDealerDrawMessage = { outputView.printDealerDrawMessage() },
        )
    }

    private fun showGameResult(game: BlackJackGame) {
        outputView.printPersonResult(game.dealer)
        game.players.forEach { player -> outputView.printPersonResult(player) }

        val gameResult = game.gameResult()
        outputView.printGameResults(gameResult)
    }

    private fun <T> retryWhenException(action: () -> T): T {
        while (true) {
            runCatching {
                return action()
            }.onFailure { e ->
                outputView.printErrorMessage(e.message)
            }
        }
    }
}
