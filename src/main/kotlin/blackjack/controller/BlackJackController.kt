package blackjack.controller

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
        return inputView.getNames().map { Player(it) }
    }

    private fun dealCards(game: BlackJackGame) {
        outputView.printFirstDrawMessage(game.players)
        game.dealCards()
        outputView.printInitialDrawMessage(game.dealer, game.players)
    }

    private fun playPlayersTurns(game: BlackJackGame) {
        game.playPlayersTurns(
            getHitFlag = { inputView.getHitFlag(it) },
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
}
