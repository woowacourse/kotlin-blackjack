package blackjack.domain.state

import blackjack.domain.CardBunchForState

class Stay(override val hand: CardBunchForState) : State
