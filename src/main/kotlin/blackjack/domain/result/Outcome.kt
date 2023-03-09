package blackjack.domain.result

import blackjack.domain.participants.User

enum class Outcome {
    WIN, DRAW, LOSE;

    companion object {
        private const val BLACKJACK_NUMBER = 21

        fun getOutcome(user: User, other: User): Outcome =
            when {
                other.getScore() > BLACKJACK_NUMBER && user.getScore() > BLACKJACK_NUMBER -> DRAW
                other.getScore() > BLACKJACK_NUMBER -> WIN
                user.getScore() > BLACKJACK_NUMBER -> LOSE

                other.getScore() == user.getScore() -> DRAW
                user.getScore() > other.getScore() -> WIN
                other.getScore() > user.getScore() -> LOSE

                else -> throw IllegalStateException()
            }
    }
}
