package blackjack.domain.model.hand.strategy

import blackjack.domain.model.Score

interface StayStrategy {
    fun isStay(score: Score): Boolean
}
