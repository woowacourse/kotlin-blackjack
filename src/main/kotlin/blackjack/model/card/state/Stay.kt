package blackjack.model.card.state

import blackjack.model.card.CardHand

class Stay(cardHand: CardHand) : Done(cardHand) {
    override fun earningRate(other: CardHandState): Double {
        if (other is BlackJack) return -1.0
        if (other is Bust) return 1.0
        return rateForStay(other)
    }

    private fun rateForStay(other: CardHandState): Double {
        if (other is Stay) {
            val score = getCardHandScore()
            val otherScore = other.getCardHandScore()
            if (score == otherScore) return 0.0
            if (score > otherScore) return 1.0
            if (score < otherScore) return -1.0
        }
        throw IllegalStateException("예기치 못한 오류")
    }
}
