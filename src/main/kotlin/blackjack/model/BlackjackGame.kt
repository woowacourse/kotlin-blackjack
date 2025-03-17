package blackjack.model

import blackjack.model.participant.Dealer
import blackjack.model.participant.Player

class BlackjackGame(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private val gameManager = GameManager(dealer, players)

    fun startGame(
        wantsToDraw: (Player) -> DrawChoice,
        printPlayerHands: (Player) -> Unit,
        printDealerHandStatus: (Boolean) -> Unit,
        printAllHands: (Dealer, List<Player>) -> Unit,
    ) {
        gameManager.dealInitialCards()
        printAllHands(dealer, players)

        if (!dealer.isBlackjack()) {
            players.forEach { drawCards(it, wantsToDraw, printPlayerHands) }
            dealerDrawCards(printDealerHandStatus)
        }
    }

    fun calculateResults(): List<Profit> = gameManager.gameResult(gameManager.calculateResultMap())

    private fun drawCards(
        player: Player,
        wantsToDraw: (Player) -> DrawChoice,
        printPlayerHands: (Player) -> Unit,
    ) {
        while (!player.isBust() && wantsToDraw(player) == DrawChoice.YES) {
            gameManager.drawCard(player)
            printPlayerHands(player)
        }
        printPlayerHands(player)
    }

    private fun dealerDrawCards(printDealerHandStatus: (Boolean) -> Unit) {
        val condition = dealer.isMoreCard()
        if (!condition) {
            printDealerHandStatus(false)
        }

        while (dealer.isMoreCard()) {
            printDealerHandStatus(true)
            gameManager.drawCard(dealer)
        }
    }
}
