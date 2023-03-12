package blackjack.domain.state

import blackjack.domain.CardBunchForState

class Burst(override val hand: CardBunchForState) : Stopped
