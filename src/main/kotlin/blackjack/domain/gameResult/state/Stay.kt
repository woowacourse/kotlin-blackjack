package blackjack.domain.gameResult.state

import blackjack.domain.participant.Participant

data class Stay(private val participant: Participant) : State {
    override val totalSum: Int
        get() = participant.getTotalSum()
    override val earnRate: Double
        get() = 1.0
}
