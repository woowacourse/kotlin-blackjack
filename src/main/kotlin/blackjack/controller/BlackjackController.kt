package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.card.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play() {
        val dealer = Dealer()
        val players = getPlayers()
        val bettingInfo = getBettingInfo(players)
        val participants = Participants(dealer, players)
        val game = BlackjackGame(Deck.create(), participants)

        startGame(game, participants)
        playGame(game)
        showGameResult(participants, bettingInfo)
    }

    private fun getPlayers(): List<Player> {
        val playerNames = inputView.readPlayerNames()
        return playerNames.map(::Player)
    }

    private fun getBettingInfo(players: List<Player>): Map<Player, Int> = players.associateWith { getBettingAmount(it.name) }

    private fun getBettingAmount(name: String): Int = inputView.readBettingAmount(name)

    private fun startGame(
        game: BlackjackGame,
        participants: Participants,
    ) {
        game.distributeInitialCards()
        outputView.printCardInfo(participants.dealer, participants.players)
    }

    private fun playGame(game: BlackjackGame) {
        game.playTurns(
            onPlayerResponse = inputView::readPlayerHit,
            onPlayerDraw = outputView::printPlayerCards,
            onDealerDraw = outputView::printDealerHit,
        )
    }

    private fun showGameResult(
        participants: Participants,
        bettingInfo: Map<Player, Int>,
    ) {
        outputView.printParticipantScore(participants.dealer, participants.players)

        val dealerProfit = participants.getDealerProfit(bettingInfo)
        val playersProfit = participants.getPlayersProfit(bettingInfo)
        outputView.printDealerProfit(participants.dealer, dealerProfit)
        outputView.printPlayersProfit(playersProfit)
    }
}
