package domain.card

class GetMinSum : SumStrategy {
    override fun getSum(handOfCards: HandOfCards) = handOfCards.getExceptAceSum() + handOfCards.countAce()
}
