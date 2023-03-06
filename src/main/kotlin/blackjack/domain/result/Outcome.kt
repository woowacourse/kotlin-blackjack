package blackjack.domain.result
import blackjack.domain.participants.User
import java.lang.IllegalStateException

enum class Outcome(val rate: Double) {
    WIN_WITH_BLACKJACK(0.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    ;

    companion object {
        fun User.winTo(other: User): Outcome = matchBlackJack(this, other)

        private fun matchBlackJack(a: User, b: User): Outcome =
            when {
                a.isBlackJack() && a.cards.size == 2 -> WIN_WITH_BLACKJACK
                a.isBlackJack() && b.isBlackJack() -> DRAW
                a.isBlackJack() -> WIN
                b.isBlackJack() -> LOSE
                else -> matchBust(a, b)
            }

        private fun matchBust(a: User, b: User): Outcome =
            when {
                a.isBust() && b.isBust() -> DRAW
                a.isBust() -> LOSE
                b.isBust() -> WIN
                else -> matchScore(a, b)
            }

        private fun matchScore(a: User, b: User): Outcome =
            when {
                a.score() > b.score() -> WIN
                a.score() == b.score() -> DRAW
                a.score() < b.score() -> LOSE
                else -> throw IllegalStateException()
            }
    }
}
