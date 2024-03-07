package blackjack.model

import blackjack.base.BaseHolder

class Dealer(override val humanName: HumanName = HumanName(DEFAULT_DEALER_NAME)) : BaseHolder() {
