package blackjack.model.user

import blackjack.model.BetMoney

class Player(
    name: String,
    val money: BetMoney,
) : Participant(name)
