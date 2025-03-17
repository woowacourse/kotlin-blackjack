package blackjack.model.domain.participant

import blackjack.model.domain.BettingMoney

data class PlayerBetAmount(
    val player: Player,
    val betAmount: BettingMoney,
) {
    fun profitResult(rate: Float): PlayerBetResult {
        return PlayerBetResult(player, betAmount.amount * rate)
    }
}
