package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class StartState(cards: Cards) : RunningState(cards) {
    constructor(vararg cards: Card) : this(Cards(*cards))

    init {
        check(!cards.isGreaterOrEqualsCardSize(LIMITED_CARD_SIZE)) { "시작 상태는 카드가 2장 미만이어야 합니다." }
    }

    override fun draw(card: Card): CardState {
        val newCards = cards.add(card)
        if (newCards.isGreaterOrEqualsCardSize(LIMITED_CARD_SIZE)) {
            return nextState(newCards)
        }
        return StartState(newCards)
    }

    private fun nextState(newCards: Cards): CardState {
        if (newCards.isBlackjack) {
            return BlackjackState(newCards)
        }
        return HitState(newCards)
    }

    companion object {
        private const val LIMITED_CARD_SIZE = 2
    }
}
