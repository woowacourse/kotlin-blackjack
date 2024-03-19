package blackjack.model.participant.state

interface HandCardState {
    fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState
}
