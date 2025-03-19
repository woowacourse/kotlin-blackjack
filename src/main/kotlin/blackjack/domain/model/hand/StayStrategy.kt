package blackjack.domain.model.hand

import blackjack.domain.model.Score

interface StayStrategy {
    fun isStay(score: Score): Boolean
}
