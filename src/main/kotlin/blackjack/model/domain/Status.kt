package blackjack.model.domain

import blackjack.model.service.Blackjack

enum class Status {
    None,
    Win,
    Lose,
    Bust,
    Draw,
    ;

    companion object {
        fun isBust(number: Int): Status {
            if (number > Blackjack.BUST_STANDARD) return Bust
            return None
        }

        fun compare(
            target: Int,
            other: Int,
        ): Status {
            if (target < other) {
                return Lose
            } else if (target > other) {
                return Win
            }
            return Draw
        }
    }
}
