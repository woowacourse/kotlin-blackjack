package state

import domain.card.Card
import domain.card.Cards

class HitState(val cards: Cards) : State {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check((cards.size > 2 && !cards.isBurst) || (cards.size == 2) && !cards.isBlackJack) { ERROR_HIT_STATE }
    }

    override fun draw(card: Card): State {
        val nextCards = cards.add2(card)
        return next(nextCards)
    }

    override fun next(nextCards: Cards): State {
        if (nextCards.isBurst) return BustState(nextCards)
        return HitState(nextCards)
    }

    fun stay(): State {
        return StayState(cards.deepCopy())
    }

    companion object {
        private const val ERROR_HIT_STATE = "카드가 두 장에 21미만이거나 세장이상에 21이하여야 합니다."
    }
}
