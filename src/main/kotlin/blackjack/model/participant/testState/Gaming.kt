package blackjack.model.participant.testState

abstract class Gaming : HandCardState {
    abstract override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState
}
