package blackjack.domain.result

import blackjack.domain.participants.User

enum class Outcome(val ratio: Double) {
    WIN(1.0), DRAW(0.0), LOSE(-1.0), BLACKJACK(1.5);

    companion object {
        private const val BLACKJACK_NUMBER = 21

        fun getOutcome(user: User, other: User): Outcome =
            when {
                other.getScore() > BLACKJACK_NUMBER && user.getScore() > BLACKJACK_NUMBER -> DRAW
                other.getScore() == user.getScore() -> DRAW
                user.isBlackJackSize() && user.isBlackJack() -> BLACKJACK

                other.getScore() > BLACKJACK_NUMBER -> WIN
                user.getScore() > BLACKJACK_NUMBER -> LOSE

                user.getScore() > other.getScore() -> WIN
                other.getScore() > user.getScore() -> LOSE

                else -> throw IllegalStateException()
            }
    }
}
