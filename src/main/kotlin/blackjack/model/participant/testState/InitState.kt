package blackjack.model.participant.testState

class InitState : Gaming() {
    override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState {
        return when {
            myScore > 21 -> Bust2()
            isHit -> Hit()
            myScore == 21 -> Blackjack2()
            else -> Stay()
        }
    }
}
