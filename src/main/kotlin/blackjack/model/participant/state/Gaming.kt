package blackjack.model.participant.state

abstract class Gaming : HandCardState {
    abstract override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState
}
