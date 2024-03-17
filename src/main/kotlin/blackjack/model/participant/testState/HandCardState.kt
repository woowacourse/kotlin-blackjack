package blackjack.model.participant.testState

interface HandCardState {
    fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState
}
