package blackjack.domain.state

import blackjack.domain.CardBunch

class BlackJack(override val hand: CardBunch) : Stopped {
    val yieldRate: Double = 0.5
}
