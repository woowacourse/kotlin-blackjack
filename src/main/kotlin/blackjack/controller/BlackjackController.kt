package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play() {
        val game = BlackjackGame(Deck.create())
        val dealer = Dealer()
        val players = getPlayers()

        startGame(game, dealer, players)
        playGame(game, dealer, players)
        showGameResult(dealer, players)
    }

    private fun getPlayers(): Players {
        val playerNames = inputView.readPlayerNames()
        return Players(playerNames.map(::Player))
    }

    private fun startGame(
        game: BlackjackGame,
        dealer: Dealer,
        players: Players,
    ) {
        game.distributeInitialCards(dealer, players)
        outputView.printCardInfo(dealer, players)
    }

    private fun playGame(
        game: BlackjackGame,
        dealer: Dealer,
        players: Players,
    ) {
        game.playPlayersTurn(
            players,
            onResponse = inputView::readPlayerHit,
            onDone = outputView::printPlayerCards,
        )
        game.playDealerTurn(dealer)
        outputView.printDealerHit(dealer)
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        outputView.printParticipantScore(dealer, players)

        val dealerResult =
            players.players
                .map { dealer.getResult(it.getScore()) }
                .groupingBy { it }
                .eachCount()
        outputView.printDealerResult(dealer, dealerResult)
        players.players.forEach {
            outputView.printPlayerResult(
                it.name,
                it.getResult(dealer.getScore()),
            )
        }
    }
}
