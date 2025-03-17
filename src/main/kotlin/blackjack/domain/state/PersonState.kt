package blackjack.domain.state

import blackjack.domain.person.Dealer
import blackjack.domain.person.Person

enum class PersonState(val isFinal: Boolean) {
    HIT(false),
    BUST(true),
    STAY(true),
    BLACKJACK(true),
    ;

    companion object {
        private const val BLACKJACK_CARD_BASE_AMOUNT = 2

        fun from(person: Person): PersonState {
            val score = person.score()
            val isDealer = person is Dealer

            return when {
                person.cards().size == BLACKJACK_CARD_BASE_AMOUNT && score.isBlackJackScore() -> BLACKJACK
                score.isBustScore() -> BUST
                isDealer && score.isDealerStayScore() -> STAY
                else -> HIT
            }
        }
    }
}
