package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.enums.UserChoice
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
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

    private fun readyForGamePlayers(): List<Participant> {
        val dealer: Participant = Dealer()
        return inputView.readPlayerName().map { Player(it) } + dealer
    }

    private fun displayPlayerNames(players: List<Participant>) {
        outputView.printNames(players.filterIsInstance<Player>())
    }

    private fun displayPlayerCards(players: List<Participant>) {
        outputView.printPlayerCards(players.filterIsInstance<Player>())
    }

    private fun displayDealerCards(players: List<Participant>) {
        outputView.printDealerCards(
            players
                .filterIsInstance<Dealer>()
                .first()
                .cards
                .first(),
        )
    }

    private fun readForGame(players: List<Participant>): BlackJackGame {
        val deck = Deck()
        return BlackJackGame(players, deck)
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
        players: List<Participant>,
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
        players: List<Participant>,
    ) {
        displayDealerExtraCard(game)
        displaySumOfParticipants(players)
        displayDealerResult(game)
        displayPlayerResult(game)
    }

    private fun displayDealerExtraCard(game: BlackJackGame) {
        outputView.printDealerExtraCard(game.processDealerTurn())
    }

    private fun displaySumOfParticipants(players: List<Participant>) {
        outputView.printDealerSum(players.filterIsInstance<Dealer>())
        outputView.printPlayerSum(players.filterIsInstance<Player>())
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
