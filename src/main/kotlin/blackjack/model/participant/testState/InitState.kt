package blackjack.model.participant.testState

class InitState : Gaming() {
    override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState {
        return when {
            isHit -> Hit()
            myScore == 21 -> Blackjack2()
            else -> Stay()
        }
    }
}
