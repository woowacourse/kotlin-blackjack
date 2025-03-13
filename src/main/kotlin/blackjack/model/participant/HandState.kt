package blackjack.model.participant

import blackjack.model.participant.Participant.Companion.INITIAL_DRAW_COUNT

enum class HandState {
    ALIVE,
    BUST,
    BLACKJACK,
    ;

    companion object {
        private const val BLACKJACK_SCORE = 21

        fun from(
            score: Int,
            cardCount: Int,
        ) = when {
            score > BLACKJACK_SCORE -> BUST
            score < BLACKJACK_SCORE -> ALIVE
            cardCount == INITIAL_DRAW_COUNT -> BLACKJACK
            else -> throw IllegalArgumentException("[ERROR] 유효하지 않은 상태입니다.")
        }
    }
}
