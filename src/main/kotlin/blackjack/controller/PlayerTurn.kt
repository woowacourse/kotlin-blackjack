package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.person.Player

class PlayerTurn(
    private val player: Player,
    private val deck: Deck,
) {
    fun play(
        askDraw: (String) -> Unit,
        getFlag: () -> Boolean,
        printDrawStatus: (Player) -> Unit,
    ) {
        while (player.canDraw) {
            askDraw(player.name)
            processOnceTurn(getFlag, printDrawStatus)
        }
    }

    private fun processOnceTurn(
        getFlag: () -> Boolean,
        printDrawStatus: (Player) -> Unit,
    ) {
        if (getFlag()) {
            player.draw(deck)
            printDrawStatus(player)
            return
        }

        player.changeToStay()
    }
}
