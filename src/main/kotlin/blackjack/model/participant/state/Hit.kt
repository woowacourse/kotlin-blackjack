package blackjack.model.participant.state

class Hit : Gaming {
    override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState {
        if (isHit) {
            if (myScore > BUST_CONDITION) {
                return Bust()
            }
            return Hit()
        } else {
            return Stay()
        }
    }

    companion object {
        private const val BUST_CONDITION = 21
    }
}
