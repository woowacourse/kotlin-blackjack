package blackjack.model.participant

import blackjack.model.card.Card

class Player(
    name: String,
) : Participant(name) {
    override fun getInitialCard(): List<Card> = cards
}
