package blackjack.model.participant.state

interface Gaming : HandCardState {
    override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState
}
