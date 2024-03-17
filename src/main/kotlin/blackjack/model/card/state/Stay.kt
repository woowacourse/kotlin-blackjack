package blackjack.model.card.state

import blackjack.model.card.CardHand

class Stay(cardHand: CardHand) : Done(cardHand) {
    override fun earningRate(other: CardHandState): Double {
        if (other is BlackJack) return -STAY_EARNING_RATE
        if (other is Bust) return STAY_EARNING_RATE
        return rateForStay(other)
    }

    private fun rateForStay(other: CardHandState): Double {
        if (other is Stay) {
            val score = getCardHandScore()
            val otherScore = other.getCardHandScore()
            if (score == otherScore) return 0.0
            if (score > otherScore) return STAY_EARNING_RATE
            if (score < otherScore) return -STAY_EARNING_RATE
        }
        throw IllegalStateException("예기치 못한 오류")
    }

    companion object {
        private const val STAY_EARNING_RATE = 1.0
    }
}
