package state

import domain.card.Card
import domain.card.Cards

class BlackJackState(val cards: Cards) : State {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(cards.isBlackJack) { ERROR_BLACKJACK_STATE }
    }

    override fun draw(card: Card): State {
        throw IllegalStateException(ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    override fun next(nextCards: Cards): State {
        throw IllegalStateException(ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    companion object {
        private const val ERROR_BLACKJACK_STATE = "카드가 두 장에 합이 21이어야 합니다."
        const val ERROR_CARD_STATE_FINISHED_DRAWN = "[ERROR] 이미 카드를 뽑는 턴이 종료되어 더이상 카드를 추가할 수 없습니다."
    }
}
