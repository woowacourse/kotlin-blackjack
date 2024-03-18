package blackjack.model.participant

import blackjack.state.State

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
                isBlackjackWin(player, dealer) -> BLACKJACK_RATE
                isBust(player) -> BUST_RATE
                isWin(player, dealer) -> WIN_RATE
                isLose(player, dealer) -> BUST_RATE
                else -> DRAW_RATE
            }
        return (player.bettingAmount.getAmount() * rate).toInt()
    }

    private fun isBlackjackWin(
        player: Player,
        dealer: Dealer,
    ): Boolean = player.state is State.Finished.Blackjack && !dealer.isBlackjack()

    private fun isBust(player: Player): Boolean = player.state is State.Finished.Bust

    private fun isWin(
        player: Player,
        dealer: Dealer,
    ): Boolean =
        (player.state is State.Finished.Stay && dealer.isBust()) ||
            (player.state is State.Finished.Stay && player.calculateScore() > dealer.calculateScore())

    private fun isLose(
        player: Player,
        dealer: Dealer,
    ): Boolean = player.state is State.Finished.Stay && player.calculateScore() < dealer.calculateScore()

    companion object {
        private const val WIN_RATE = 1.0
        private const val BLACKJACK_RATE = 1.5
        private const val BUST_RATE = -1.0
        private const val DRAW_RATE = 0.0
    }
}
