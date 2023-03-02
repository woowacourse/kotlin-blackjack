package blackjack.domain

import blackjack.domain.BlackJackGame.Companion.BLACKJACK_NUMBER
import java.lang.IllegalStateException

enum class Outcome {
    WIN, DRAW, LOSE;

    companion object {
        fun of(dealer: Dealer, guest: Guest): Outcome =
            when {
                dealer.score > BLACKJACK_NUMBER && guest.score > BLACKJACK_NUMBER -> DRAW
                dealer.score > BLACKJACK_NUMBER -> WIN
                guest.score > BLACKJACK_NUMBER -> LOSE

                dealer.score == guest.score -> DRAW
                guest.score > dealer.score -> WIN
                dealer.score > guest.score -> LOSE

                else -> throw IllegalStateException()
            }
    }
}
