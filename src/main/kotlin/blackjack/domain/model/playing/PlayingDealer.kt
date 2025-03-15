package blackjack.domain.model.playing

import blackjack.domain.model.Card
import blackjack.domain.model.HandState
import blackjack.domain.model.Hands
import blackjack.domain.model.Hands.Companion.BUST_THRESHOLD

class PlayingDealer(override var hands: Hands, override val name: String = DEALER_NAME) : PlayingParticipant() {
    constructor(vararg card: Card) : this(Hands(card.toList()))

    override fun getHandsState(): HandState {
        val score = getScore()
        val handState =
            when {
                score == BUST_THRESHOLD && isStartCardCount() -> HandState.BLACKJACK
                score > BUST_THRESHOLD -> HandState.BUST
                score > DEALER_DRAW_THRESHOLD -> HandState.STAY
                else -> HandState.HIT
            }
        return handState
    }

    fun isHit(): Boolean = getScore() <= DEALER_DRAW_THRESHOLD

    companion object {
        private const val DEALER_NAME = "딜러"
        const val DEALER_DRAW_THRESHOLD = 16
    }
}
