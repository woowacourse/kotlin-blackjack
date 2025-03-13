package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.result.GameResult

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
        getIsHit: (String) -> Boolean,
        printDrawStatus: (Player) -> Unit,
    ) {
        players.forEach { player ->
            playPlayerTurns(player, getIsHit, printDrawStatus)
        }
    }

    fun playDealerTurns(printDealerDrawMessage: () -> Unit) {
        while (dealer.isDrawable()) {
            printDealerDrawMessage()
            dealer.draw(deck)
        }
    }

    fun gameResult(): GameResult {
        return GameResult(dealer, players)
    }

    private fun playPlayerTurns(
        player: Player,
        getIsHit: (String) -> Boolean,
        printDrawStatus: (Player) -> Unit,
    ) {
        while (player.isDrawable()) {
            playPlayerTurn(player, getIsHit(player.name), printDrawStatus)
        }
    }

    private fun playPlayerTurn(
        player: Player,
        isHit: Boolean,
        printDrawStatus: (Player) -> Unit,
    ) {
        if (isHit) {
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
