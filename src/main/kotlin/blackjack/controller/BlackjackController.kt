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
        game.dealInitialCards()
        outputView.printCardInfo(participants)
    }

    private fun playGame(game: BlackjackGame) {
        game.playTurns(
            shouldContinue = { participant ->
                when (participant) {
                    is Player -> inputView.readPlayerHit(participant)
                    is Dealer -> true
                }
            },
            onDraw = { participant ->
                when (participant) {
                    is Player -> outputView.printCards(participant)
                    is Dealer -> outputView.printHitOnce(participant)
                }
            },
        )
    }

    private fun showGameResult(
        participants: Participants,
        bettingInfo: Map<Player, BettingAmount>,
    ) {
        outputView.printParticipantScore(participants)

        val blackjackResult = participants.blackjackResult()
        val dealerProfit = blackjackResult.dealerProfit(bettingInfo)
        val playersProfit = blackjackResult.playersProfit(bettingInfo)
        outputView.printParticipantsProfit(participants, dealerProfit, playersProfit)
    }
}
