package blackjack.domain.state

import blackjack.domain.CardBunch

interface State {
    val hand: CardBunch
}
