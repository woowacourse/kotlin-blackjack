package blackjack.model.game

import blackjack.model.user.Hand

sealed interface GameState {
    fun judgeState(hand: Hand): GameState {
        val point = hand.score.point
        return when {
            point > BLACKJACK_POINT -> Finished.BUST
            point == BLACKJACK_POINT && hand.cards.size == BLACKJACK_CARD_SIZE ->
                Finished.BLACKJACK

            else -> Running.HIT
        }
    }

    companion object {
        private const val BLACKJACK_CARD_SIZE = 2
        private const val BLACKJACK_POINT = 21
    }

    enum class Running : GameState {
        HIT,
    }

    enum class Finished : GameState {
        STAY,
        BUST,
        BLACKJACK,
    }
}
