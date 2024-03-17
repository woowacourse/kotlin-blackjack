package blackjack.model.participant.testState

import blackjack.model.deck.HandCards

class Hit : Gaming() {
    override fun nextTurn(
        handCards: HandCards,
        isHit: Boolean,
    ): HandCardState {
        if (isHit) {
            if (handCards.calculateCardScore() > 21) {
                return Bust2()
            }
            return Hit()
        } else {
            return Stay()
        }
    }
}
