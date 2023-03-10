package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.participants.Dealer
import blackjack.domain.result.Outcome

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

    override fun matchWith(dealer: Dealer): Outcome {
        throw IllegalStateException("초기 세팅 전에는 결과를 비교할 수 없습니다.")
    }
}
