package blackjack.model.participant

import blackjack.model.ResultCalculator.BLACKJACK_NUMBER
import blackjack.model.ResultCalculator.adjustScore
import blackjack.model.card.Card

class Player(
    name: String,
) : Participant(name) {
    override fun getInitialCard(): List<Card> = cards

    override fun isBust() = adjustScore > BLACKJACK_NUMBER
}
