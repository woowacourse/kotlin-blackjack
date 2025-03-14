package blackjack.domain.model.progress

import blackjack.domain.model.participant.Player

class BetHistory(
    private val _playerBets: MutableMap<Player, Int> = mutableMapOf(),
) {
    val playerBets: Map<Player, Int>
        get() = _playerBets.toMap()

    fun addBetLog(
        player: Player,
        betAmount: BetAmount,
    ) {
        _playerBets[player] = betAmount.betAmount
    }
}
