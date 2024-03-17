package blackjack.model.participant.testState

import blackjack.model.deck.HandCards

class InitState : Gaming() {
    override fun nextTurn(
        handCards: HandCards,
        isHit: Boolean,
    ): HandCardState {
        require(handCards.cards.size == 2) {
            "초기 카드는 2장으로 제한됩니다."
        }

        return when {
            isHit -> Hit()
            handCards.calculateCardScore() == 21 -> Blackjack2()
            else -> Stay()
        }
    }
}
