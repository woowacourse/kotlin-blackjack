package blackjack.domain.model.hand.strategy

import blackjack.domain.model.Score

class PlayerStay : StayStrategy {
    override fun isStay(score: Score): Boolean = score.isMaxScore()
}
