package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class BlackjackState(cards: Cards) : FinishedState(cards) {
    constructor(vararg cards: Card) : this(Cards(*cards))

    init {
        check(cards.isBlackjack()) { "카드의 총 합이 21점이 아닙니다." }
    }
}
