package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.UserChoice
import blackjack.domain.card.CardFactory
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.util.retryWhenException
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardFactory: CardFactory,
) {
    fun run() {
        val participants = readyForParticipants()
        displayPlayerNames(participants)
        val game = makeGame(participants)
        startGame(game, participants)
        endGame(game, participants)
    }

    private fun readyForParticipants(): Participants {
        return retryWhenException(
            action = {
                val players = readPlayers().map { Player(it) }
                Participants(players + Dealer())
            },
            onError = { message ->
                outputView.printErrorMessage(message)
            },
        )
    }

    private fun readPlayers(): List<PlayerState> {
        return inputView.readPlayerName().map {
            val bettingAmount = readBettingAmount(it)
            PlayerState(it, bettingAmount)
        }
    }

    private fun readBettingAmount(name: String): BettingAmount {
        return retryWhenException(
            action = {
                val input = inputView.readBettingAmount(name)
                BettingAmount(input)
            },
            onError = { message ->
                outputView.printErrorMessage(message)
            },
        )
    }

    private fun displayPlayerNames(participants: Participants) {
        outputView.printNames(participants.players)
    }

    private fun displayPlayerCards(participants: Participants) {
        outputView.printPlayerCards(participants.players)
    }

    private fun displayDealerCards(participants: Participants) {
        outputView.printDealerCards(participants.dealer)
    }

    private fun makeGame(participants: Participants): BlackJackGame {
        val deck = Deck(cardFactory)
        return BlackJackGame(participants, deck)
    }

    private fun getUserChoice(name: String): UserChoice =
        retryWhenException(
            action = {
                val input = inputView.readHitOrStay(name)
                UserChoice.from(input)
            },
            onError = { message ->
                outputView.printErrorMessage(message)
            },
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
            game.playGame(
                getPlayerChoice = { playerName ->
                    getUserChoice(playerName)
                },
                onPlayerStateUpdated = { player ->
                    outputView.printOneCardMessage(player)
                },
            )
        }.onFailure {
            outputView.printErrorMessage(it.message)
        }
    }

    private fun displayResult(
        game: BlackJackGame,
        participants: Participants,
    ) {
        displayDealerExtraCard(game)
        displaySumOfParticipants(participants)
        displayDealerResult(game)
        displayPlayerResult(game)
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

    private fun displayDealerResult(game: BlackJackGame) {
        val result = game.calculateDealerResult()
        outputView.printDealerResult(result)
    }

    private fun displayPlayerResult(game: BlackJackGame) {
        game.calculatePlayerResult { name, result ->
            outputView.printPlayerResult(name, result)
        }
    }
}
