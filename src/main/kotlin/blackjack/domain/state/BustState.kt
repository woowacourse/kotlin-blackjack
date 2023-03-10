package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class BustState(val cards: Cards) : FinishedState(cards) {
    constructor(vararg cards: Card) : this(Cards(*cards))

    init {
        check(cards.isBust()) { "버스트 상태는 21점을 초과해야 합니다." }
    }
}
