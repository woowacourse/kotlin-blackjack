package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class HitState(cards: Cards) : RunningState(cards) {
    constructor(vararg cards: Card) : this(Cards(*cards))

    init {
        check(cards.isGreaterOrEqualsCardSize(MINIMUM_CARDS_SIZE)) { "히트 상태는 카드를 최소 2장 보유해야 합니다." }
    }

    override fun draw(card: Card): CardState {
        cards.add(card)
        if (cards.isBust) return BustState(cards)
        return this
    }

    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
    }
}
