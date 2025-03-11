package blackjack.model.participant

import blackjack.model.ResultCalculator.BUST_NUMBER
import blackjack.model.ResultCalculator.adjustScore
import blackjack.model.card.Card

class Player(
    name: String,
) : Participant(name) {
    override fun getInitialCard(): List<Card> = cards

    override fun isBust() = adjustScore(cards) > BUST_NUMBER
}
