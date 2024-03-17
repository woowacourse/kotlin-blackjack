package blackjack.model.participant.state

class Hit : Gaming() {
    override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState {
        if (isHit) {
            if (myScore > 21) {
                return Bust2()
            }
            return Hit()
        } else {
            return Stay()
        }
    }
}
