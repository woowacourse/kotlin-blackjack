package blackjack.model

fun interface DecisionCondition {
    fun isHit(cardHandSum: Int): Boolean
}

class DealerDecisionCondition : DecisionCondition {
    override fun isHit(cardHandSum: Int): Boolean = cardHandSum <= 16
}
