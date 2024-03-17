package blackjack.model.participant.state

class Hit : Gaming() {
    override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState {
        if (isHit) {
            if (myScore > BLACKJACK_NUMBER) {
                return Bust2()
            }
            return Hit()
        } else {
            return Stay()
        }
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
