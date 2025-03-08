package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
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
    fun play(dealer: Dealer) {
        val players: List<Player> = inputView.readPlayerNames()

        outputView.printInitialHandOutCardMessage(players)
        val gameManager = GameManager(dealer, players)
        gameManager.dealInitialCardWithCount(INITIAL_HAND_OUT_CARD_COUNT)
        outputView.printAllPlayerHands(dealer, players)

        playersDrawCards(gameManager, players)

        dealerDrawCards(dealer)

        outputView.printFinalHandStatus(dealer, players)

        resultSummary(gameManager)
    }

    private fun playersDrawCards(
        gameManager: GameManager,
        players: List<Player>,
    ) {
        players.forEach { player -> playerDrawOrStay(gameManager, player) }
    }

    private fun playerDrawOrStay(
        gameManager: GameManager,
        player: Player,
    ) {
        val userChoice: DrawChoice = inputView.readMoreCardCondition(player)
        if (!userChoice.isDraw()) {
            outputView.printPlayerHands(player)
            return
        }
        gameManager.drawCard(player)
        outputView.printPlayerHands(player)
        if (player.isBust()) return
        playerDrawOrStay(gameManager, player)
    }

    private fun dealerDrawCards(dealer: Dealer) {
        val moreCard = dealer.isMoreCard()
        if (moreCard) {
            dealer.addCard(Deck.draw())
        }
        outputView.printDealerHandStatus(moreCard)
    }

    private fun resultSummary(gameManager: GameManager) {
        val result = gameManager.calculateResultMap()
        val dealerResult = gameManager.calculateDealerResult(result)
        outputView.printFinalResult(result, dealerResult)
    }
}
