package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player

class BlackJackGame(
    val dealer: Dealer,
    players: List<Player>,
    private val deck: Deck = Deck(),
) {
    val players = players.toList()

    fun dealCards() {
        repeat(FIRST_TURN_DRAW_AMOUNT) {
            dealer.draw(deck)
            players.forEach { player -> player.draw(deck) }
        }
    }

    fun playPlayersTurns(
        getHitFlag: (String) -> Boolean,
        printDrawStatus: (Player) -> Unit,
    ) {
        players.forEach { player ->
            playPlayerTurns(player, getHitFlag, printDrawStatus)
        }
    }

    fun playDealerTurns(printDealerDrawMessage: () -> Unit) {
        while (dealer.canDraw) {
            printDealerDrawMessage()
            dealer.draw(deck)
        }
    }

    fun gameResult(): GameResult {
        return GameResult(dealer, players)
    }

    private fun playPlayerTurns(
        player: Player,
        getHitFlag: (String) -> Boolean,
        printDrawStatus: (Player) -> Unit,
    ) {
        while (player.canDraw) {
            playPlayerTurn(player, getHitFlag(player.name), printDrawStatus)
        }
    }

    private fun playPlayerTurn(
        player: Player,
        hitFlag: Boolean,
        printDrawStatus: (Player) -> Unit,
    ) {
        if (hitFlag) {
            player.draw(deck)
            printDrawStatus(player)
            return
        }

        player.changeToStay()
    }

    companion object {
        private const val FIRST_TURN_DRAW_AMOUNT = 2
    }
}
