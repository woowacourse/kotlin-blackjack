package blackjack.model

import blackjack.model.ResultCalculator.BUST_NUMBER
import blackjack.model.ResultCalculator.adjustScore

class Player(
    name: String,
) : Person(name) {
    override fun isBust() = adjustScore(cards) > BUST_NUMBER
}
