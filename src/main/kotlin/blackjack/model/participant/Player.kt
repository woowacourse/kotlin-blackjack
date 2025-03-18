package blackjack.model.participant

import blackjack.model.amount.BetAmount
import blackjack.model.card.Card

class Player(
    name: String,
    val betAmount: BetAmount,
) : Participant(name) {
    override fun getInitialCard(): List<Card> = cards
}
