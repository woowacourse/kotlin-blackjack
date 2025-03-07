package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.DrawChoice
import blackjack.model.GameManager
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private lateinit var gameManager: GameManager

    fun play(dealer: Dealer) {
        val players: List<Player> = inputView.readPlayerNames()

        outputView.printInitialHandOutCardMessage(players)
        gameManager = GameManager(dealer, players)
        gameManager.dealInitialCardWithCount(INITIAL_HAND_OUT_CARD_COUNT)
        outputView.printAllPlayerHands(dealer, players)

        playersDrawCards(players)

        dealerDrawCards(dealer)

        outputView.printFinalHandStatus(dealer, players)

        resultSummary(gameManager)
    }

    private fun playersDrawCards(players: List<Player>) {
        players.forEach { player -> playerDrawOrStay(player) }
    }

    private fun playerDrawOrStay(player: Player) {
        val condition: DrawChoice = inputView.readMoreCardCondition(player)
        if (condition.isStay()) {
            outputView.printPlayerHands(player)
            return
        }
        gameManager.drawCard(player)
        outputView.printPlayerHands(player)
        if (player.isBust()) return
        playerDrawOrStay(player)
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
        val dealerResult = gameManager.calculateDealerResult(result)
        outputView.printFinalResult(result, dealerResult)
    }
}
