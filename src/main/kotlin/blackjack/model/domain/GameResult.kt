package blackjack.model.domain

import blackjack.model.domain.card.Hand

enum class GameResult() {
    None,
    Win,
    Lose,
    Draw,
    ;

    companion object {
        fun isBust(number: Int): GameResult {
            if (number > Hand.BUST_STANDARD) return Lose
            return None
        }

        fun compare(
            target: Int,
            other: Int,
        ): GameResult {
            if (target < other) {
                return Lose
            } else if (target > other) {
                return Win
            }
            return Draw
        }
    }
}
