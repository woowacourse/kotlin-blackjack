package blackjack.domain.state

import blackjack.const.GameRule
import blackjack.domain.ScoreCalculator
import blackjack.domain.person.Player

enum class PlayerState(override val isFinal: Boolean) : PersonState {
    FIRST_TURN(false),
    HIT(false),
    BUST(true),
    STAY(true),
    ;

    companion object {
        fun from(player: Player): PlayerState {
            val cards = player.cards()
            return when {
                cards.size < GameRule.FIRST_TURN_DRAW_AMOUNT -> FIRST_TURN
                ScoreCalculator.calculate(cards) > GameRule.BLACKJACK_SCORE -> BUST
                else -> HIT
            }
        }
    }
}
