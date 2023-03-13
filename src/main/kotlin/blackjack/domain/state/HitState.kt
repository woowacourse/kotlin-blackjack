package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class HitState(cards: Cards) : RunningState(cards) {
    constructor(vararg cards: Card) : this(Cards(*cards))

    init {
        check(cards.isGreaterOrEqualsCardSize(MINIMUM_CARDS_SIZE)) { "히트 상태는 카드를 최소 2장 보유해야 합니다." }
    }

    override fun draw(card: Card): CardState {
        val newCards = cards.add(card)
        if (newCards.isBust) return BustState(newCards)
        return HitState(newCards)
    }

    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
    }
}
