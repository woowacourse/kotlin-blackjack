package blackjack.model.participant

import blackjack.state.Blackjack
import blackjack.state.Bust
import blackjack.state.Stay

class Judge {
    fun gameResult(
        dealer: Dealer,
        players: List<Player>,
    ): Map<String, Int> =
        players.associate { player ->
            player.name to calculateProfit(dealer, player)
        }

    fun calculateProfit(
        dealer: Dealer,
        player: Player,
    ): Int {
        val rate =
            when {
                player.state is Blackjack && !dealer.isBlackjack() -> BLACKJACK_RATE
                player.state is Bust -> BUST_RATE
                player.state is Stay && dealer.isBust() -> WIN_RATE
                player.state is Stay && player.calculateScore() > dealer.calculateScore() -> WIN_RATE
                player.state is Stay && player.calculateScore() < dealer.calculateScore() -> BUST_RATE
                else -> DRAW_RATE
            }
        return (player.bettingAmount.getAmount() * rate).toInt()
    }

    companion object {
        private const val WIN_RATE = 1.0
        private const val BLACKJACK_RATE = 1.5
        private const val BUST_RATE = -1.0
        private const val DRAW_RATE = 0.0
    }
}
