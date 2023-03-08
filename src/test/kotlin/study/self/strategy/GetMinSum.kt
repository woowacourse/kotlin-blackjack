package study.self.strategy/*
package domain.card.strategy

import domain.card.HandOfCards

object GetMinSum : SumStrategy {
    override fun getSum(handOfCards: HandOfCards) = handOfCards.getExceptAceSum() + handOfCards.countAce()
}
*/
