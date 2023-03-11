package blackjack.domain.state.inTurn

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.result.Outcome
import blackjack.domain.state.State
import blackjack.domain.state.endTurn.BlackJack

class FirstTurn private constructor(cards: Cards) : InTurn(cards) {
    constructor() : this(Cards())

    override fun draw(card: Card): State {
        val newCards: Cards = cards + card
        return when {
            newCards.size == 1 -> FirstTurn(newCards)
            newCards.calculateScore().isBlackJack -> BlackJack(newCards)
            else -> Hit(newCards)
        }
    }

    override fun matchWith(otherState: State): Outcome {
        throw IllegalStateException("초기 세팅 전에는 결과를 비교할 수 없습니다.")
    }
}
