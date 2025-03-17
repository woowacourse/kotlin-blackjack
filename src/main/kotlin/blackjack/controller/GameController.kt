package blackjack.controller

import blackjack.domain.model.Bet
import blackjack.domain.model.Game
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.Hand
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import blackjack.domain.model.result.Scoreboard
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val game = makeGame()
        initialize(game)
        play(game)
        announceResults(game)
    }

    private fun makeGame(): Game {
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

    private fun initialize(game: Game) {
        game.processBets { player -> retryOnError { Bet(inputView.readPlayerBet(player)) } }
        game.showStatus(outputView::printParticipantInitialStatus)
        outputView.printInitialDeals(game)
    }

    private fun play(game: Game) {
        game.processPlayersHits(
            { player -> retryOnError { inputView.readPlayerAction(player) } },
            outputView::printParticipantStatus,
        )
        game.processDealerHits(outputView::printDealerHit)
    }

    private fun announceResults(game: Game) {
        game.showStatus(outputView::printParticipantStatusWithPoint)
        outputView.printFinalResult(Scoreboard(game.dealer, game.players))
    }

    private fun <T> retryOnError(function: () -> T): T {
        return runCatching { function() }.getOrElse { error ->
            println(error.message)
            retryOnError(function)
        }
    }
}
