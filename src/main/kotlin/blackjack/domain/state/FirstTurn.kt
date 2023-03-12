package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class FirstTurn private constructor(cards: Cards) : State(cards) {
    constructor() : this(Cards())

    override fun draw(card: Card): State {
        val newCards: Cards = cards + card
        return when {
            newCards.size < HIT_CARD_COUNT -> FirstTurn(newCards)
            newCards.calculateScore().isBlackJack -> BlackJack(newCards)
            else -> Hit(newCards)
        }
    }

    override fun matchWith(otherState: State): Outcome {
        throw IllegalStateException(ERROR_MESSAGE_DRAW_TWO_CARD)
    }

    companion object {
        private const val HIT_CARD_COUNT = 2
        private const val ERROR_MESSAGE_DRAW_TWO_CARD = "초기 세팅 전에는 결과를 비교할 수 없습니다."
    }
}
