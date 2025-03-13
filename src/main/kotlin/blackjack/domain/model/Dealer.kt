package blackjack.domain.model

import blackjack.domain.model.Hands.Companion.BUST_THRESHOLD

class Dealer(override var hands: Hands, override val name: String = DEALER_NAME) : Participant() {
    constructor(vararg card: Card) : this(Hands(card.toList()))

    override fun showInitCards(): List<Card> = showCards(INIT_VISIBLE_CARD_COUNT)

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
        private const val INIT_VISIBLE_CARD_COUNT = 1
    }
}
