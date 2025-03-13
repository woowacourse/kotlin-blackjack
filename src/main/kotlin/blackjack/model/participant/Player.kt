package blackjack.model.participant

import blackjack.model.card.Card

class Player(
    name: String,
) : Participant(name) {
    var betAmount: Int = 0

    var finalProfit: Double = 0.0

    override fun getInitialCard(): List<Card> = cards
}
