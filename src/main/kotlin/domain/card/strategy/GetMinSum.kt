package domain.card.strategy

import domain.card.HandOfCards

class GetMinSum : SumStrategy {
    override fun getSum(handOfCards: HandOfCards) = handOfCards.getExceptAceSum() + handOfCards.countAce()
}
