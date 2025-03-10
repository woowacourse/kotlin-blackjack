package blackjack.domain.state

import blackjack.domain.ScoreCalculator.BLACKJACK_SCORE
import blackjack.domain.calculateScore
import blackjack.domain.person.Player

enum class PlayerState(override val isFinal: Boolean) : PersonState {
    HIT(false),
    BUST(true),
    STAY(true),
    ;

    companion object {
        fun from(player: Player): PlayerState {
            val score = player.calculateScore()
            return when {
                score > BLACKJACK_SCORE -> BUST
                else -> HIT
            }
        }
    }
}
