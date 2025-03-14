package blackjack.model.state

import blackjack.model.ScoreCalculator.BUST_NUMBER
import blackjack.model.card.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT

enum class GameStatus {
    BLACKJACK,
    BUST,
    IN_PROGRESS, ;

    companion object {
        fun of(
            score: Int,
            cardCount: Int,
        ): GameStatus {
            if (score == BUST_NUMBER && cardCount == INITIAL_HAND_OUT_CARD_COUNT) return BLACKJACK
            if (score > BUST_NUMBER) return BUST
            return IN_PROGRESS
        }
    }
}
