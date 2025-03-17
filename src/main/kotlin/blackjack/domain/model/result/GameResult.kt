package blackjack.domain.model.result

import blackjack.domain.model.Money
import blackjack.domain.model.participant.Player

enum class GameResult(private val profitRate: Double) {
    WIN(1.0),
    LOSE(-1.0),
    PUSH(0.0),
    BLACKJACK_WIN(0.5),
    BLACKJACK_LOSE(-0.5),
    ;

    fun cashOut(player: Player): Money {
        return (player.bet.amount * profitRate)
    }
}
