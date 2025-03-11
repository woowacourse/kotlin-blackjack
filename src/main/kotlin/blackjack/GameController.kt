package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.card.Card
import blackjack.domain.gameResult.PlayerResults
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
        return inputView.getPlayerNames().map { playerName ->
            Player(playerName)
        }
    }

    private fun showResult(game: BlackJackGame) {
        outputView.printFinalCards(game)
        val result = PlayerResults(game)
        outputView.printGameResult(result)
    }
}
