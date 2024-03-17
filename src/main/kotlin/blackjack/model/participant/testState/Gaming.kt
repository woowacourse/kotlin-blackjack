package blackjack.model.participant.testState

import blackjack.model.deck.HandCards

abstract class Gaming : HandCardState {
    abstract fun nextTurn(
        handCards: HandCards,
        isHit: Boolean,
    ): HandCardState
}
