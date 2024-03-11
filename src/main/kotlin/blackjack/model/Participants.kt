package blackjack.model

import blackjack.base.BaseHolder
import blackjack.model.UserState.BLACKJACK
import blackjack.model.UserState.BUST
import blackjack.model.UserState.STAY

class Participants(
    val dealer: Dealer,
    val playerGroup: PlayerGroup,
) {
    fun initSetting(gameDeck: GameDeck) {
        repeat(INITIAL_CARD_COUNTS) {
            dealer.takeCard(gameDeck.drawCard())
            playerGroup.players.forEach { player ->
                player.takeCard(gameDeck.drawCard())
            }
        }
    }

    fun matchResult() {
        playerGroup.players.forEach { player ->
            when (dealer.status.state) {
                BUST -> checkBust(player = player)
                BLACKJACK -> checkBlackJack(player = player)
                STAY -> checkStay(player = player)
                else -> throw IllegalStateException()
            }
        }
    }

    private fun checkBust(player: Player) {
        if (player.status.state == BUST) {
            winAndLose(winner = dealer, loser = player)
        } else {
            winAndLose(winner = player, loser = dealer)
        }
    }

    private fun checkBlackJack(player: Player) {
        if (player.status.state == BLACKJACK) {
            draw(player = player, dealer = dealer)
        } else {
            winAndLose(winner = dealer, loser = player)
        }
    }

    private fun checkStay(player: Player) {
        if (player.status.state == BUST) {
            winAndLose(winner = dealer, loser = player)
        } else {
            compareWhenBothStay(player)
        }
    }

    private fun compareWhenBothStay(player: Player) {
        val dealerPoint = dealer.status.hand.calculate()
        val playerPoint = player.status.hand.calculate()

        if (dealerPoint == playerPoint) {
            draw(player = player, dealer = dealer)
        } else if (dealerPoint > playerPoint) {
            winAndLose(winner = dealer, loser = player)
        } else {
            winAndLose(winner = player, loser = dealer)
        }
    }

    private fun winAndLose(
        winner: BaseHolder,
        loser: BaseHolder,
    ) {
        winner.changeResult(newWin = INCREASING_COUNT)
        loser.changeResult(newDefeat = INCREASING_COUNT)
    }

    private fun draw(
        player: BaseHolder,
        dealer: BaseHolder,
    ) {
        player.changeResult(newPush = INCREASING_COUNT)
        dealer.changeResult(newPush = INCREASING_COUNT)
    }

    companion object {
        const val INITIAL_CARD_COUNTS = 2
        private const val INCREASING_COUNT = 1
    }
}
