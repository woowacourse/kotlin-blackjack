package blackjack.model.domain

import blackjack.model.service.Blackjack

enum class GameResult() {
    None,
    Win,
    Lose,
    Draw,
    ;

    companion object {
        fun isBust(number: Int): GameResult {
            if (number > Blackjack.BUST_STANDARD) return Lose
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
