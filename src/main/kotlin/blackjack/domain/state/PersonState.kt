package blackjack.domain.state

import blackjack.domain.Score.Companion.BLACKJACK_SCORE
import blackjack.domain.person.Dealer
import blackjack.domain.person.Person

enum class PersonState(val isFinal: Boolean) {
    HIT(false),
    BUST(true),
    STAY(true),
    BLACKJACK(true),
    ;

    companion object {
        private const val DEALER_ADDITIONAL_DRAW_BASE_SCORE = 16

        fun from(person: Person): PersonState {
            val score = person.score()
            val isDealer = person is Dealer

            return when {
                person.cards().size == 2 && person.score() == BLACKJACK_SCORE -> BLACKJACK
                score > BLACKJACK_SCORE -> BUST
                isDealer && score > DEALER_ADDITIONAL_DRAW_BASE_SCORE -> STAY
                else -> HIT
            }
        }
    }
}
