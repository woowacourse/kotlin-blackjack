package blackjack.controller

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
    private lateinit var gameManager: GameManager

    fun play(dealer: Dealer) {
        val players = playerSetting()

        playBlackjack(dealer, players)

        outputView.printFinalHandStatus(dealer, players)
        resultSummary(gameManager)
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

    private fun playBlackjack(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.printInitialHandOutCardMessage(players)
        gameManager = GameManager(dealer, players)
        gameManager.dealInitialCards()
        outputView.printAllPlayerHands(dealer, players)

        if (!dealer.isBlackjack()) {
            playersDrawCards(players)
            dealerDrawCards(dealer)
        }
    }

    private fun playersDrawCards(players: List<Player>) {
        players.forEach { player -> playerDrawOrStay(player) }
    }

    private fun playerDrawOrStay(player: Player) {
        while (!player.isBust()) {
            val playerChoice = inputView.readMoreCardCondition(player)
            when (DrawChoice.from(playerChoice)) {
                DrawChoice.YES -> {
                    gameManager.drawCard(player)
                    outputView.printPlayerHands(player)
                }

                DrawChoice.NO -> {
                    outputView.printPlayerHands(player)
                    break
                }

                null -> continue
            }
        }
    }

    private fun dealerDrawCards(dealer: Dealer) {
        val moreCard = dealer.isMoreCard()
        if (moreCard) {
            gameManager.drawCard(dealer)
        }
        outputView.printDealerHandStatus(moreCard)
    }

    private fun resultSummary(gameManager: GameManager) {
        val result = gameManager.calculateResultMap()
        val profitResult = gameManager.gameResult(result)
        outputView.printFinalResult(profitResult)
    }
}
