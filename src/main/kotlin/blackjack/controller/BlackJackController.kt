package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Money
import blackjack.domain.ParticipantCards
import blackjack.domain.deck.ShuffledDeck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val participants = readyForGameParticipants()
        displayPlayerNames(participants)
        val game = readForGame(participants)
        startGame(game, participants)
        displayResult(game, participants)
        displayProfit(participants)
    }

    private fun readyForGameParticipants(): Participants {
        val dealer = Dealer(ParticipantCards())
        return Participants(dealer, inputView.readPlayerNames().map { name -> Player(name, ParticipantCards(), readBettingMoney(name)) })
    }

    private fun readBettingMoney(name: String): Money = Money(inputView.readBettingMoney(name))

    private fun displayPlayerNames(participants: Participants) {
        outputView.printNames(participants.players)
    }

    private fun displayPlayerCards(participants: Participants) {
        outputView.printPlayerCards(participants.players)
    }

    private fun displayDealerCards(participants: Participants) {
        outputView.printDealerCards(participants.dealer.showInitialCards())
    }

    private fun readForGame(participants: Participants): BlackJackGame = BlackJackGame(participants, ShuffledDeck())

    private fun getUserChoice(name: String): Boolean = inputView.readHitOrStay(name)

    private fun startGame(
        game: BlackJackGame,
        participants: Participants,
    ) {
        game.handOutInitializedCards()
        displayDealerCards(participants)
        displayPlayerCards(participants)

        game.choice(
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

    private fun displayProfit(participants: Participants) {
        displayDealerProfit(participants)
        displayPlayerProfit(participants)
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

    private fun displayPlayerProfit(participants: Participants) {
        participants.players.forEach { player ->
            val profit = player.getProfit(player.getResult(participants.dealer))
            outputView.printPlayerProfit(player.name, profit)
        }
    }

    private fun displayDealerProfit(participants: Participants) {
        var finalProfit = 0.0

        participants.players.forEach { player ->
            val profit = participants.dealer.getProfit(player, participants.dealer.getResult(player))
            finalProfit += profit
        }

        outputView.printDealerProfit(finalProfit)
    }
}
