package blackjack.domain

import java.lang.IllegalStateException

enum class Outcome {
    WIN, DRAW, LOSE;

    companion object {
        fun of(dealer: User, user: User): Outcome =
            when {
                dealer.score > 21 && user.score > 21 -> DRAW
                dealer.score > 21 -> WIN
                user.score > 21 -> LOSE

                dealer.score == user.score -> DRAW
                user.score > dealer.score -> WIN
                dealer.score > user.score -> LOSE

                else -> throw IllegalStateException()
            }
    }
}
