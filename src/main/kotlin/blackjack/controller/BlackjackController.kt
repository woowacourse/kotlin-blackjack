package blackjack.controller

import blackjack.domain.BettingAmount
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
        val participants = getParticipants()
        val bettingInfo = getBettingInfo(participants.players)
        val game = BlackjackGame(Deck.create(), participants)

        startGame(game, participants)
        playGame(game)
        showGameResult(participants, bettingInfo)
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

    private fun getBettingInfo(players: List<Player>): Map<Player, BettingAmount> =
        players.associateWith {
            getBettingAmount(it.name)
        }

    private fun getBettingAmount(name: String): BettingAmount {
        val amount = inputView.readBettingAmount(name)
        return BettingAmount(amount)
    }

    private fun startGame(
        game: BlackjackGame,
        participants: Participants,
    ) {
        game.distributeInitialCards()
        outputView.printCardInfo(participants.dealer, participants.players)
    }

    private fun playGame(game: BlackjackGame) {
        game.playTurns(
            onPlayerChoice = inputView::readPlayerHit,
            onPlayerDraw = outputView::printPlayerCards,
            onDealerDraw = outputView::printDealerHit,
        )
    }

    private fun showGameResult(
        participants: Participants,
        bettingInfo: Map<Player, BettingAmount>,
    ) {
        outputView.printParticipantScore(participants.dealer, participants.players)

        val dealerProfit = participants.getDealerProfit(bettingInfo)
        val playersProfit = participants.getPlayersProfit(bettingInfo)
        outputView.printDealerProfit(participants.dealer, dealerProfit)
        outputView.printPlayersProfit(playersProfit)
    }
}
