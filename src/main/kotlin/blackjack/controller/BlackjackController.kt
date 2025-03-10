package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.DrawChoice
import blackjack.model.GameManager
import blackjack.model.Player
import blackjack.model.card.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT
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
        gameManager.distributeInitialCardWithCount(INITIAL_HAND_OUT_CARD_COUNT)
        outputView.printAllPlayerHands(dealer, players)

        playersDrawCards(gameManager, players)
        dealerDrawCards(gameManager, dealer)

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
        while (true) {
            val choice: DrawChoice = inputView.readCardDrawChoice(player)
            if (gameManager.distributeCardWithChoice(choice, player)) {
                outputView.printPlayerHands(player)
                if (player.isBust()) break
                continue
            }
            outputView.printPlayerHands(player)
            break
        }
    }

    private fun dealerDrawCards(
        gameManager: GameManager,
        dealer: Dealer,
    ) {
        val isDraw = dealer.isAvailDrawCard()
        if (isDraw) gameManager.distributeCard(dealer)
        outputView.printDealerHandStatus(isDraw)
    }

    private fun resultSummary(gameManager: GameManager) {
        val result = gameManager.calculateResultMap()
        val dealerResult = gameManager.calculateDealerResult(result)
        outputView.printFinalResult(result, dealerResult)
    }
}
