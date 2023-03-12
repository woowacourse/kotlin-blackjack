package state

import domain.card.Card
import domain.card.Cards

class FirstState(val cards: Cards = Cards(listOf())) : State {
    init {
        check(cards.size < 2) { ERROR_FIRST_STATE }
    }

    override fun draw(card: Card): State {
        val nextCards = cards.add2(card)
        return next(nextCards)
    }

    override fun next(nextCards: Cards): State {
        if (nextCards.size == 2) {
            if (nextCards.isBlackJack) return BlackJackState(nextCards)
            return HitState(nextCards)
        }
        return FirstState(nextCards)
    }

    companion object {
        private const val ERROR_FIRST_STATE = "카드가 두 장 미만어야 합니다."
    }
}
