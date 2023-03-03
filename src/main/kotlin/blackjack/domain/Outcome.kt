package blackjack.domain

import blackjack.domain.BlackJackGame.Companion.BLACKJACK_NUMBER
import java.lang.IllegalStateException

enum class Outcome {
    WIN, DRAW, LOSE;

    companion object {
        fun User.winTo(other: User): Outcome =
            when {
                other.score > BLACKJACK_NUMBER && this.score > BLACKJACK_NUMBER -> DRAW
                other.score > BLACKJACK_NUMBER -> WIN
                this.score > BLACKJACK_NUMBER -> LOSE

                other.score == this.score -> DRAW
                this.score > other.score -> WIN
                other.score > this.score -> LOSE

                else -> throw IllegalStateException()
            }
    }
}
