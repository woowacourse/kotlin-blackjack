package blackjack.model

import blackjack.model.ResultType.Companion.BUST_NUMBER
import blackjack.model.ScoreCalculator.calculateOptimalSum

class Player(
    name: String,
) : Participant(name) {
    override fun isBust(): Boolean = calculateOptimalSum(cards) > BUST_NUMBER
}
