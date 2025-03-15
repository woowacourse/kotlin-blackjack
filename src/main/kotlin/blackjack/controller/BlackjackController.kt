package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.model.DrawChoice
import blackjack.model.GameManager
import blackjack.model.amount.BetAmount
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play(dealer: Dealer) {
        val players = playerSetting()
        val blackjackGame = BlackjackGame(dealer, players)

        outputView.printInitialHandOutCardMessage(players)
        startBlackjackGame(blackjackGame, dealer, players)

        outputView.printFinalHandStatus(dealer, players)
        val result = blackjackGame.calculateResults()
        outputView.printFinalResult(result)
    }

    private fun startBlackjackGame(
        blackjackGame: BlackjackGame,
        dealer: Dealer,
        players: List<Player>
    ) {
        blackjackGame.startGame(
            wantsToDraw = { player -> getValidDrawChoice(player) },
            printPlayerHands = { player -> outputView.printPlayerHands(player) },
            printDealerHandStatus = { status -> outputView.printDealerHandStatus(status) },
            printAllHands = { _, _ -> outputView.printAllPlayerHands(dealer, players) }
        )
    }

    private fun playerSetting(): List<Player> {
        var playerNames: List<String>? = null
        while (playerNames == null) {
            playerNames = inputView.readPlayerNames()
        }

        return playerNames.map { name ->
            var betAmount: Int? = null
            while (betAmount == null) {
                betAmount = inputView.readBetAmount(name)
            }
            Player(name, BetAmount(betAmount))
        }
    }

    private fun getValidDrawChoice(player: Player): DrawChoice {
        while (true) {
            val input = inputView.readMoreCardCondition(player)
            val choice = DrawChoice.from(input)
            if (choice != null) {
                return choice
            }
        }
    }
}
