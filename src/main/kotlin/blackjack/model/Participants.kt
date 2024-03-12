package blackjack.model

import blackjack.state.State.Finished.Blackjack
import blackjack.state.State.Finished.Bust
import blackjack.state.State.Finished.Stay

class Participants(
    val dealer: Dealer,
    val playerGroup: PlayerGroup,
) {
    fun initParticipantsDeck() {
        dealer.takeCard(card = GameDeck.drawCard())
        playerGroup.players.forEach { player ->
            player.takeCard(card = GameDeck.drawCard())
        }
    }

    fun resetHand() {
        playerGroup.players.forEach { player -> player.hand.reset() }
        dealer.hand.reset()
    }

    fun matchResult() {
        playerGroup.players.forEach { player ->
            when (dealer.state) {
                is Bust -> checkBust(player = player)
                is Blackjack -> checkBlackJack(player = player)
                is Stay -> checkStay(player = player)
                else -> throw IllegalStateException()
            }
        }
    }

    private fun checkBust(player: Player) {
        if (player.state is Bust) {
            winAndLose(winner = dealer, loser = player)
        } else {
            winAndLose(winner = player, loser = dealer)
        }
    }

    private fun checkBlackJack(player: Player) {
        if (player.state is Blackjack) {
            draw(player = player, dealer = dealer)
        } else {
            winAndLose(winner = dealer, loser = player)
        }
    }

    private fun checkStay(player: Player) {
        if (player.state is Bust) {
            winAndLose(winner = dealer, loser = player)
        } else {
            compareWhenBothStay(player = player)
        }
    }

    private fun compareWhenBothStay(player: Player) {
        val dealerPoints = dealer.hand.calculate()
        val playerPoints = player.hand.calculate()

        when {
            dealerPoints == playerPoints -> draw(player = player, dealer = dealer)
            dealerPoints > playerPoints -> winAndLose(winner = dealer, loser = player)
            else -> winAndLose(winner = player, loser = dealer)
        }
    }

    private fun winAndLose(
        winner: CardHolder,
        loser: CardHolder,
    ) {
        winner.hand.changeResult(newWin = INCREASING_COUNT)
        loser.hand.changeResult(newDefeat = INCREASING_COUNT)
    }

    private fun draw(
        player: CardHolder,
        dealer: CardHolder,
    ) {
        player.hand.changeResult(newPush = INCREASING_COUNT)
        dealer.hand.changeResult(newPush = INCREASING_COUNT)
    }

    companion object {
        const val INITIAL_CARD_COUNTS = 2
        const val INCREASING_COUNT = 1
    }
}
