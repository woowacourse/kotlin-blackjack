package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val shuffledCards: List<Card> = Card.getAllCard().shuffled()
    private val deck = Deck(shuffledCards)

    fun run() {
        val players: List<Player> = getPlayers()
        val game = BlackJackGame(players, deck, inputView, outputView)
        game.setUp()
        outputView.showInitialCards(game)

        game.eachPlayerHitOrNot()

        if (game.hasDealerAdditionalCard()) {
            outputView.printDealerHaveAdditionalCard()
        }

        outputView.printFinalCards(game)
        showResult(game)
    }

    private fun getPlayers(): List<Player> {
        return inputView.getPlayerNames().map { playerName ->
            Player(playerName)
        }
    }

    private fun showResult(game: BlackJackGame) {
        val result = GameResult(game)
        outputView.printGameResult(result.getAllPlayerResult())
    }
}
