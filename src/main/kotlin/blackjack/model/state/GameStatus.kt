package blackjack.model.state

import blackjack.model.Score
import blackjack.model.card.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT

enum class GameStatus {
    BLACKJACK,
    BUST,
    STAY,
    ;

    companion object {
        fun of(
            score: Score,
            cardCount: Int,
        ): GameStatus {
            if (score.isBlackjackNumber() && cardCount == INITIAL_HAND_OUT_CARD_COUNT) return BLACKJACK
            if (score.isBust()) return BUST
            return STAY
        }
    }
}
