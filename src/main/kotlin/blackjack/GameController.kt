package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.card.Card
import blackjack.domain.deck.Deck
import blackjack.domain.gameResult.GameResults
import blackjack.domain.participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.blackjackView.BlackJackInputView
import blackjack.view.blackjackView.BlackJackOutputView

class GameController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val shuffledCards: List<Card> = Card.getAllCard().shuffled()
    private val deck = Deck(shuffledCards)

    fun run() {
        val players: List<Player> = getPlayers()
        val game = BlackJackGame(players, deck, BlackJackOutputView, BlackJackInputView)
        game.setUp()
        game.run()
        showResult(game)
    }

    private fun getPlayers(): List<Player> {
        val input = inputView.getPlayerNames()
        return input.map { playerName ->
            val bettingAmount =
                retryUntilValidInput {
                    inputView.getPlayerBettingAmount(playerName)
                }
            runCatchingUntilValidInput {
                Player(playerName, bettingAmount)
            }
        }
    }

    private fun <T> retryUntilValidInput(
        count: Int = 3,
        msg: String = "올바르지 않은 형식",
        action: () -> T?,
    ): T {
        var tried = 0
        var result: T? = null
        while (result == null && tried < count) {
            result = action()
            tried++
        }
        requireNotNull(result) { msg }
        return result
    }

    private fun <T> runCatchingUntilValidInput(action: () -> T?): T {
        return retryUntilValidInput {
            runCatching {
                action()
            }.getOrNull()
        }
    }

    private fun showResult(game: BlackJackGame) {
        outputView.printFinalCards(game)
        val result = GameResults(game)
        outputView.printGameResult(result)
    }

    companion object {
        const val RETRY_COUNT = 3
        const val ERR_INVALID_FORMAT = "올바르지 않은 형식입니다"
    }
}
