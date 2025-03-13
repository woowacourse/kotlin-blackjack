package blackjack.domain.model

import blackjack.domain.model.Hands.Companion.BUST_THRESHOLD

class Player(override var hands: Hands, override val name: String) : Participant() {
    constructor(name: String, vararg card: Card) : this(Hands(card.toList()), name)

    override fun showInitCards() = showCards(INIT_VISIBLE_CARD_COUNT)

    override fun getHandsState(): HandState {
        val score = getScore()
        return when {
            score == BUST_THRESHOLD && isStartCardCount() -> HandState.BLACKJACK
            score > BUST_THRESHOLD -> HandState.BUST
            else -> HandState.HIT
        }
    }

    companion object {
        private const val INIT_VISIBLE_CARD_COUNT = 2
    }
}
