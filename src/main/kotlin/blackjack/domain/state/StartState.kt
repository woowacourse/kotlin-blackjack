package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class StartState(cards: Cards) : RunningState(cards) {
    constructor(vararg cards: Card) : this(Cards(*cards))

    init {
        check(!cards.isGreaterOrEqualsCardSize(LIMITED_CARD_SIZE)) { "시작 상태는 카드가 2장 미만이어야 합니다." }
    }

    override fun draw(card: Card): CardState {
        cards.add(card)
        if (cards.isGreaterOrEqualsCardSize(LIMITED_CARD_SIZE)) {
            return nextState()
        }
        return this
    }

    private fun nextState(): CardState {
        if (cards.isBlackjack) {
            return BlackjackState(cards)
        }
        return HitState(cards)
    }

    companion object {
        private const val LIMITED_CARD_SIZE = 2
    }
}
