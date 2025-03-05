package blackjack.domain

enum class GameState {
    BLACKJACK,
    BUST,
    STAY,
    HIT,
    ;

    companion object {
        fun from(score: Int): GameState {
            return when {
                score == 21 -> BLACKJACK
                score > 21 -> BUST
                else -> HIT
            }
        }

        fun checkState(state: GameState): Boolean {
            return state == STAY || state == BUST
        }
    }
}
