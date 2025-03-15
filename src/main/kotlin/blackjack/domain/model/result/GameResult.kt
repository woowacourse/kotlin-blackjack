package blackjack.domain.model.result

import blackjack.domain.model.participant.Player

enum class GameResult(private val profitRate: Double) {
    WIN(1.0),
    LOSE(-1.0),
    BLACKJACK(0.5),
    PUSH(0.0),
    ;

    fun cashOut(player: Player): Int {
        return Math.round(player.bet.amount * profitRate).toInt()
    }
}
