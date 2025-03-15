package blackjack.domain.model.playing

import blackjack.domain.model.Card
import blackjack.domain.model.HandState
import blackjack.domain.model.Hands
import blackjack.domain.model.Hands.Companion.BUST_THRESHOLD

class PlayingPlayer(override var hands: Hands, override val name: String) : PlayingParticipant() {
    constructor(name: String, vararg card: Card) : this(Hands(card.toList()), name)

    override fun getHandsState(): HandState {
        val score = getScore()
        return when {
            score == BUST_THRESHOLD && isStartCardCount() -> HandState.BLACKJACK
            score > BUST_THRESHOLD -> HandState.BUST
            else -> HandState.HIT
        }
    }
}
