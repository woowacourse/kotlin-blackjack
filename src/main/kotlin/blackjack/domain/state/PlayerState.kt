package blackjack.domain.state

import blackjack.const.GameRule
import blackjack.domain.person.Player

enum class PlayerState(override val isFinal: Boolean) : PersonState {
    FIRST_TURN(false),
    HIT(false),
    BLACKJACK(false),
    BUST(true),
    STAY(true),
    ;

    companion object {
        fun from(player: Player): PlayerState =
            when {
                player.cards().size < GameRule.FIRST_TURN_DRAW_AMOUNT -> FIRST_TURN
                player.score() == GameRule.BLACKJACK_SCORE -> BLACKJACK
                player.score() > GameRule.BLACKJACK_SCORE -> BUST
                else -> HIT
            }
    }
}
