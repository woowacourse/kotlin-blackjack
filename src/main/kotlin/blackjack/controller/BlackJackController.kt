package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.enums.UserChoice
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.util.retryWhenException
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val players = readyForGamePlayers()
        displayPlayerNames(players)
        val game = readForGame(players)
        startGame(game, players)
        displayResult(game, players)
    }

    private fun readyForGamePlayers(): Participants {
        val dealer: Participant = Dealer()
        return retryWhenException(
            action = {
                val players = inputView.readPlayerName().map { Player(it) }
                Participants(players + dealer)
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
        outputView.printDealerCards(participants.dealer.cards.first())
    }

    private fun readForGame(participants: Participants): BlackJackGame {
        val deck = Deck()
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
        displayDealerCards(players)
        displayPlayerCards(players)

        game.playGame(
            getPlayerChoice = { playerName ->
                getUserChoice(playerName)
            },
            onPlayerStateUpdated = { player ->
                outputView.printOneCardMessage(player)
            },
        )
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
        outputView.printDealerExtraCard(game.processDealerTurn())
    }

    private fun displaySumOfParticipants(participants: Participants) {
        outputView.printDealerSum(participants.dealer)
        outputView.printPlayerSum(participants.players)
    }

    private fun displayDealerResult(game: BlackJackGame) {
        game.calculateDealerResult { result ->
            outputView.printDealerResult(result)
        }
    }

    private fun displayPlayerResult(game: BlackJackGame) {
        game.calculatePlayerResult { name, result ->
            outputView.printPlayerResult(name, result)
        }
    }
}
