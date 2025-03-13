package blackjack.controller

import blackjack.domain.model.Bet
import blackjack.domain.model.Dealer
import blackjack.domain.model.Deck
import blackjack.domain.model.Game
import blackjack.domain.model.Hand
import blackjack.domain.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val game = initializeGame()
        game.processPlayersBets { player -> retryOnError { Bet(inputView.readPlayerBet(player)) } }
        outputView.printInitialDeals(game)
        outputView.printParticipantStatus(game.dealer)
        game.players.forEach { player -> outputView.printParticipantStatus(player) }
        processHits(game)
        outputView.printResults(game)
    }

    private fun initializeGame(): Game {
        val deck = Deck()
        val game =
            retryOnError {
                Game(
                    deck,
                    Dealer(deck.draw(Hand.STARTING_HAND_SIZE)),
                    inputView.readPlayerNames().map { playerName ->
                        Player(playerName, deck.draw(Hand.STARTING_HAND_SIZE))
                    },
                )
            }
        return game
    }

    private fun processHits(game: Game) {
        game.processPlayersHits(
            { player -> retryOnError { inputView.readPlayerAction(player) } },
            outputView::printParticipantStatus,
        )
        game.processDealerHits(outputView::printDealerHit)
    }

    private fun <T> retryOnError(function: () -> T): T {
        return runCatching { function() }.getOrElse { error ->
            println(error.message)
            retryOnError(function)
        }
    }
}
