package blackjack.controller

import blackjack.domain.BettingAmount
import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.UserChoice
import blackjack.domain.card.cardFactoryImpl
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerState
import blackjack.util.retryWhenException
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.model.DealerUiModel
import blackjack.view.model.PlayerUiModel

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val participants = readyForParticipants()
        displayPlayerNames(participants)
        val game = makeGame(participants)
        startGame(game, participants)
        endGame(game, participants)
    }

    private fun readyForParticipants(): Participants =
        retryWhenException(
            action = {
                val players = readPlayers().map(::Player)
                Participants(players + Dealer())
            },
            onError = outputView::printErrorMessage,
        )

    private fun readPlayers(): List<PlayerState> = inputView.readPlayerName().map { PlayerState(it, readBettingAmount(it)) }

    private fun readBettingAmount(name: String): BettingAmount =
        retryWhenException(
            action = { BettingAmount(inputView.readBettingAmount(name)) },
            onError = outputView::printErrorMessage,
        )

    private fun displayPlayerNames(participants: Participants) {
        outputView.printNames(participants.players)
    }

    private fun displayPlayerCards(participants: Participants) {
        outputView.printPlayerCards(participants.players)
    }

    private fun displayDealerCards(participants: Participants) {
        outputView.printDealerCards(participants.dealer)
    }

    private fun makeGame(participants: Participants): BlackJackGame = BlackJackGame(participants, Deck(cardFactoryImpl()))

    private fun getUserChoice(name: String): UserChoice =
        retryWhenException(
            action = { UserChoice.from(inputView.readHitOrStay(name)) },
            onError = outputView::printErrorMessage,
        )

    private fun startGame(
        game: BlackJackGame,
        players: Participants,
    ) {
        game.handOutInitializedCards()
        displayInitialCards(players)
        playGame(game)
    }

    private fun displayInitialCards(players: Participants) {
        displayDealerCards(players)
        displayPlayerCards(players)
    }

    private fun playGame(game: BlackJackGame) {
        runCatching {
            game.playerTurn(
                getPlayerChoice = ::getUserChoice,
                onPlayerStateUpdated = outputView::printOneCardMessage,
            )
        }.onFailure {
            outputView.printErrorMessage(it.message)
        }
    }

    private fun endGame(
        game: BlackJackGame,
        participants: Participants,
    ) {
        displayDealerExtraCard(game)
        displaySumOfParticipants(participants)
        displayDealerResult(participants)
        displayPlayerResult(participants)
    }

    private fun displayDealerExtraCard(game: BlackJackGame) {
        when (val drawCount = game.processDealerTurn()) {
            0 -> return
            else -> outputView.printDealerExtraCard(drawCount)
        }
    }

    private fun displaySumOfParticipants(participants: Participants) {
        outputView.printDealerSum(participants.dealer)
        outputView.printPlayerSum(participants.players)
    }

    private fun displayDealerResult(participants: Participants) {
        val profit =
            participants.players
                .filter { participants.dealer.compare(it) in listOf(GameResult.BLACKJACK, GameResult.WIN) }
                .sumOf { it.money }
                .toDouble()

        outputView.printDealerResult(DealerUiModel(profit))
    }

    private fun displayPlayerResult(player: Participants) {
        val result =
            player.players.map {
                val dividend = it.compare(player.dealer).dividend
                val money = it.profit(dividend)
                PlayerUiModel(money, it.name)
            }
        outputView.printPlayerResult(result)
    }
}
