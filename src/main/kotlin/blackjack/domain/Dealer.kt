package blackjack.domain

import blackjack.domain.state.Deal
import blackjack.domain.state.Running
import blackjack.domain.state.State

class Dealer(name: ParticipantName = ParticipantName("딜러")) : Participant(name) {

    override var state: State = Deal(Hand(listOf()))

    fun shouldHit(): Boolean = state.hasJustRunning() && state.getScore() <= HIT_STANDARD_SCORE

    private fun State.hasJustRunning(): Boolean = this is Running && hand.size == Running.MIN_HAND_SIZE

    override fun receive(card: Card) {
        super.receive(card)
        if (state is Running && shouldHit().not()) state = state.stay()
    }

    companion object {
        const val HIT_STANDARD_SCORE = 16
    }
}
