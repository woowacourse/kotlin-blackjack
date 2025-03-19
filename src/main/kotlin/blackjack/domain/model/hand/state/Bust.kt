package blackjack.domain.model.hand.state

import blackjack.domain.model.hand.Hands

class Bust(hands: Hands) : Finished(hands)
