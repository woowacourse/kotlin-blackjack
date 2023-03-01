package blackjack.domain

import blackjack.domain.BlackJackGame.Companion.BLACKJACK_NUMBER
import java.lang.IllegalStateException

enum class Outcome {
    WIN, DRAW, LOSE;

    companion object {
        fun of(dealer: User, user: User): Outcome =
            when {
                dealer.score > BLACKJACK_NUMBER && user.score > BLACKJACK_NUMBER -> DRAW
                dealer.score > BLACKJACK_NUMBER -> WIN
                user.score > BLACKJACK_NUMBER -> LOSE

                dealer.score == user.score -> DRAW
                user.score > dealer.score -> WIN
                dealer.score > user.score -> LOSE

                else -> throw IllegalStateException()
            }
    }
}
