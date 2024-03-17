package blackjack.model.participant.state

class InitState : Gaming() {
    override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState {
        return when {
            myScore > BLACKJACK_NUMBER -> Bust2()
            isHit -> Hit()
            myScore == BLACKJACK_NUMBER -> Blackjack2()
            else -> Stay()
        }
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
