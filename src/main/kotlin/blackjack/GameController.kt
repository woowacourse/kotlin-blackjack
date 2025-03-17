package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.card.Card
import blackjack.domain.deck.Deck
import blackjack.domain.gameResult.PlayerResult
import blackjack.domain.participant.BlackJackPair
import blackjack.domain.participant.Player
import blackjack.global.ThrowableRetry
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.blackjackView.BlackJackInputView
import blackjack.view.blackjackView.BlackJackOutputView

class GameController(
    private val inputView: InputView,
    private val outputView: OutputView,
) : ThrowableRetry {
    private val shuffledCards: List<Card> = Card.getAllCard().shuffled()
    private val deck = Deck(shuffledCards)

    fun run() {
        val players =
            runCatchingUntilValidInput(RETRY_COUNT) {
                getPlayers()
            }
        val pair = BlackJackPair(players)
        val game = BlackJackGame(pair, deck, BlackJackOutputView, BlackJackInputView)
        game.setUp()
        game.run()
        showResult(pair)
    }

    private fun getPlayers(): List<Player> {
        val input = inputView.getPlayerNames()
        return input.map { playerName ->
            val bettingAmount = inputView.getPlayerBettingAmount(playerName)
            Player(playerName, bettingAmount)
        }
    }

    private fun showResult(pair: BlackJackPair) {
        outputView.printFinalCards(pair)
        val result = PlayerResult.createResultList(pair)
        outputView.printGameResult(result)
    }

    override fun onOnceFailure(e: Throwable) {
        outputView.printOnException(e)
    }

    companion object {
        const val RETRY_COUNT = 3
    }
}
