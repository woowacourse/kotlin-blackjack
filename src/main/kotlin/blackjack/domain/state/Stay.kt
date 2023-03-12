package blackjack.domain.state

import blackjack.domain.CardBunch

class Stay(override val hand: CardBunch) : Stopped
