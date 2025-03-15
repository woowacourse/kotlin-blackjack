package blackjack.model.user

import blackjack.model.Money

class Player(
    name: String,
    val money: Money,
) : Participant(name)
