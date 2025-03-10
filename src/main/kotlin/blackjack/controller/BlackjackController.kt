package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play() {
        val participants = getParticipants()
        val game = BlackjackGame(Deck.create(), participants)

        startGame(game, participants)
        playGame(game)
        showGameResult(participants)
    }

    private fun getParticipants(): Participants {
        val dealer = Dealer()
        val players = getPlayers()
        return Participants(dealer, players)
    }

    private fun getPlayers(): List<Player> {
        val playerNames = inputView.readPlayerNames()
        return playerNames.map(::Player)
    }

    private fun startGame(
        game: BlackjackGame,
        participants: Participants,
    ) {
        game.distributeInitialCards()
        outputView.printCardInfo(participants.dealer, participants.players)
    }

    private fun playGame(game: BlackjackGame) {
        game.playPlayersTurn(
            onResponse = inputView::readPlayerHit,
            onDraw = outputView::printPlayerCards,
        )
        game.playDealerTurn(
            onDraw = outputView::printDealerHit,
        )
    }

    private fun showGameResult(participants: Participants) {
        outputView.printParticipantScore(participants.dealer, participants.players)

        val dealerResult = participants.getDealerResult()
        outputView.printDealerResult(participants.dealer, dealerResult)

        val playersResult = participants.getPlayerResults()
        playersResult.forEach { (name, result) ->
            outputView.printPlayerResult(name, result)
        }
    }
}
