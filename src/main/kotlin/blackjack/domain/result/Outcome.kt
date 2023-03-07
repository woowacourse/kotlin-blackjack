package blackjack.domain.result

import blackjack.domain.participants.User

enum class Outcome {
    WIN, DRAW, LOSE;

    companion object {
        private const val BLACKJACK_NUMBER = 21

        fun User.winTo(other: User): Outcome =
            when {
                other.getScore() > BLACKJACK_NUMBER && this.getScore() > BLACKJACK_NUMBER -> DRAW
                other.getScore() > BLACKJACK_NUMBER -> WIN
                this.getScore() > BLACKJACK_NUMBER -> LOSE

                other.getScore() == this.getScore() -> DRAW
                this.getScore() > other.getScore() -> WIN
                other.getScore() > this.getScore() -> LOSE

                else -> throw IllegalStateException()
            }
    }
}
